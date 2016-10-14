SUMMARY = "Graphics packages for MagOS"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

MACHINE_GSTREAMER_1_0_PLUGIN ??= ""
MACHINE_HWCODECS ??= ""

RDEPENDS_${PN} = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston weston-init weston-examples gtk+3-demo', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'packagegroup-core-directfb', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'packagegroup-core-x11-base', '', d)} \
    ${MACHINE_GSTREAMER_1_0_PLUGIN} \
    ${MACHINE_HWCODECS} \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-rtsp-server \
"
