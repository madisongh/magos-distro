SUMMARY = "Base MagOS image"

LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-magos-base \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_LINGUAS = " "

inherit core-image
