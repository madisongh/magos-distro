DESCRIPTION = "NVIDIA Tegra Multimedia API headers and examples"
HOMEPAGE = "http://developer.nvidia.com"
LICENSE = "Proprietary & BSD"

SRC_URI = "http://developer.download.nvidia.com/embedded/L4T/r24_Release_v2.2/Tegra_Multimedia_API_R${PV}_aarch64.tbz2 \
	   file://make-x11-conditional.patch \
           file://fix-tools-makefile.patch \
           file://remove-xxd-reference.patch \
"
SRC_URI[md5sum] = "5e6fa9a39b20e07c2d4da3647a13a647"
SRC_URI[sha256sum] = "78367e2fb15adf460fe8cc8cb8d4f0f2e166abb61e16ea9bbad707692928276c"

COMPATIBLE_MACHINE = "(tegra210)"
PACKAGE_ARCH = "${SOC_FAMILY_PKGARCH}"

DEPENDS = "tegra-mmapi tegra-libraries virtual/egl virtual/libgles1 virtual/libgles2 jpeg expat gstreamer1.0 glib-2.0 v4l-utils tensorrt opencv coreutils-native"

LIC_FILES_CHKSUM = "file://LICENSE;md5=60ad17cc726658e8cf73578bea47b85f \
		    file://argus/LICENSE.TXT;md5=271791ce6ff6f928d44a848145021687"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)}"
PACKAGECONFIG[x11] = "-DWITH_X11=ON,,virtual/libx11 gtk+3"

inherit cmake pkgconfig cuda

S = "${WORKDIR}/tegra_multimedia_api"
B = "${S}"

OECMAKE_SOURCEPATH = "${S}/argus"
EXTRA_OECMAKE = "-DMULTIPROCESS=ON \
                 -DCMAKE_INCLUDE_PATH=${STAGING_INCDIR}/libjpeg-8b-tegra \
                 -DJPEG_NAMES=libnvjpeg.so"

do_configure() {
    rm -rf ${S}/include
    #sed -i -e's,\(samples/11\),#\1,' ${S}/Makefile
    find samples '-(' -name 'Rules.mk' -o -name 'Makefile' '-)' -exec sed -i -e's,^LDFLAGS,NVLDFLAGS,' -e's,\$(LDFLAGS),$(LDFLAGS) $(NVLDFLAGS),' {} \;
    cd ${OECMAKE_SOURCEPATH}
    cmake_do_configure
}

do_compile() {
    oe_runmake -C ${S}/argus
    DRMFLAGS="`pkg-config --cflags libdrm`"
    CPP=`echo ${CXX} | sed -e's, .*,,'`
    CXX_EXTRA=`echo ${CXX} | sed -e's,^[^ ]*,,'`
    oe_runmake -j1 -e all TEGRA_ARMABI=${TARGET_ARCH} TARGET_ROOTFS=${STAGING_DIR_TARGET} CUDA_PATH=${STAGING_DIR_NATIVE}/usr/local/cuda-8.0 \
                      CPPFLAGS="${CXX_EXTRA} ${CXXFLAGS} -std=c++11 ${DRMFLAGS} -I${STAGING_INCDIR}/libjpeg-8b-tegra -I${STAGING_DIR_TARGET}/usr/local/cuda-8.0/include -I${S}/samples/common/algorithm/cuda -I${S}/argus/samples/utils" NVCCFLAGS="--shared --std=c++11" LDFLAGS="-L${STAGING_DIR_TARGET}/usr/local/cuda-8.0/lib ${LDFLAGS} -lpthread " CFLAGS="${CFLAGS} `pkg-config --cflags opencv`"
}

do_install() {
    oe_runmake -C ${S}/argus "DESTDIR=${D}" install
    install -d ${D}/opt/tegra-mmapi
    cp -R --preserve=mode,timestamps ${S}/data ${D}/opt/tegra-mmapi/
    install -d ${D}/opt/tegra-mmapi/bin
    install -m 0755 ${S}/samples/00_video_decode/video_decode ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/01_video_encode/video_encode ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/02_video_dec_cuda/video_dec_cuda ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/03_video_cuda_enc/video_cuda_enc ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/05_jpeg_encode/jpeg_encode ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/06_jpeg_decode/jpeg_decode ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/07_video_convert/video_convert ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/09_camera_jpeg_capture/camera_jpeg_capture ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/09_camera_jpeg_capture/camera_jpeg_capture ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/10_camera_recording/camera_recording ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/11_camera_object_identification/camera_caffe ${D}/opt/tegra-mmapi/bin/
    install -m 0755 ${S}/samples/backend/backend ${D}/opt/tegra-mmapi/bin/
}

FILES_${PN} += "/opt/tegra-mmapi"
RDEPENDS_${PN} += "tegra-libraries-libv4l-plugins"
