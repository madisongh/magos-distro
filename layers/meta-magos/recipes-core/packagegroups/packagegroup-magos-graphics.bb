SUMMARY = "Graphics packages for MagOS"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = " \
    ${@base_contains('DISTRO_FEATURES', 'wayland', 'weston weston-init weston-examples gtk+3-demo', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'directfb', 'packagegroup-core-directfb', '', d)} \
    ${MACHINE_GSTREAMER_1_0_PLUGIN} \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-rtsp-server \
"
