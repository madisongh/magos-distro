SUMMARY = "MagOS image for production"

LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-dropbear package-management hwcodecs"

IMAGE_FSTYPES = "sdcard"

IMAGE_INSTALL = " \
    packagegroup-magos-base \
    packagegroup-magos-mmctools \
    packagegroup-core-full-cmdline \
    ${@base_contains('DISTRO_FEATURES', 'wayland', 'weston weston-init weston-examples gtk+3-demo', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'directfb', 'packagegroup-core-directfb', '', d)} \
    ${MACHINE_GSTREAMER_1_0_PLUGIN} \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-rtsp-server \
"

IMAGE_LINGUAS = " "

inherit core-image
