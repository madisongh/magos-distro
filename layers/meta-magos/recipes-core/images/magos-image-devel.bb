SUMMARY = "MagOS image for development"

require magos-image-prod.bb

IMAGE_FEATURES += "dev-pkgs tools-debug tools-profile tools-testapps debug-tweaks"

IMAGE_INSTALL += "kernel-dev \
    packagegroup-magos-buildtools \
    packagegroup-fsl-gstreamer \
    packagegroup-fsl-gstreamer-full \
    packagegroup-fslc-gstreamer1.0-full \
    gstreamer1.0-rtsp-server \
"
