require magos-image-base.bb

IMAGE_FEATURES += "debug-tweaks tools-testapps ssh-server-dropbear"

CORE_IMAGE_EXTRA_INSTALL += "\
    packagegroup-magos-mmctools \			 
    packagegroup-fsl-gstreamer \
    packagegroup-fsl-gstreamer-full \
    packagegroup-fslc-gstreamer1.0-full \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-testapps \
    packagegroup-core-directfb \
    imx-test \
    util-linux \
"
