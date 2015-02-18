SUMMARY = "Base MagOS image"

LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-magos-base \
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_LINGUAS = " "

inherit core-image
