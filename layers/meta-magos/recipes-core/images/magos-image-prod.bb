SUMMARY = "MagOS image for production"

LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-dropbear package-management hwcodecs"

IMAGE_INSTALL = " \
    packagegroup-magos-base \
    packagegroup-magos-mmctools \
    packagegroup-core-full-cmdline \
    ${@base_contains('DISTRO_FEATURES', 'wayland', 'weston weston-init weston-examples gtk+3-demo clutter-1.0-examples', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'directfb', 'packagegroup-core-directfb', '', d)} \
    packagegroup-fsl-gstreamer \
    packagegroup-fsl-gstreamer-full \
    packagegroup-fslc-gstreamer1.0-full \
    packagegroup-fslc-gstreamer1.0 \
    packagegroup-fslc-gstreamer1.0-audio \
    packagegroup-fslc-gstreamer1.0-debug \
    packagegroup-fslc-gstreamer1.0-video \
    packagegroup-fslc-gstreamer1.0-base \
    gstreamer1.0-rtsp-server \
"

IMAGE_LINGUAS = " "

inherit core-image
