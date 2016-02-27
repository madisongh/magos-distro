SUMMARY = "MagOS image for production"

LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-dropbear package-management hwcodecs"

IMAGE_FSTYPES = "ext3"
IMAGE_FSTYPES_wandboard = "sdcard"

MAGOS_GRAPHICS ?= "packagegroup-magos-graphics"
MAGOS_GRAPHICS_jetson-tx1 = "lib32-packagegroup-magos-graphics"

IMAGE_INSTALL = " \
    packagegroup-magos-base \
    packagegroup-magos-mmctools \
    packagegroup-core-full-cmdline \
    ${MAGOS_GRAPHICS} \
"

IMAGE_LINGUAS = " "

generate_imx_sdcard_append() {
    parted -s ${SDCARD} set 1 boot on
}
_generate_boot_image_append() {
    mcopy -i ${WORKDIR}/boot.img -s ${IMAGE_ROOTFS}/boot/extlinux ::/
}
inherit core-image

EXTRA_IMAGECMD_append_ext4 = " -O ^flex_bg"
