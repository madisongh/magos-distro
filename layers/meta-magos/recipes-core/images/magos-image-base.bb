SUMMARY = "Base MagOS image"

LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-magos-base \
    ${ROOTFS_PKGMANAGE_BOOTSTRAP} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_LINGUAS = " "

generate_imx_sdcard_append() {
    parted -s ${SDCARD} set 1 boot on
}
_generate_boot_image_append() {
    mcopy -i ${WORKDIR}/boot.img -s ${IMAGE_ROOTFS}/boot/extlinux ::/
}
inherit core-image
