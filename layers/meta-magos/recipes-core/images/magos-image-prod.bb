SUMMARY = "MagOS image for production"

LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-dropbear package-management hwcodecs"

IMAGE_FSTYPES = "sdcard"
IMAGE_FSTYPES_append_jetson-tx1 = " tegraflash"

MAGOS_GRAPHICS ?= "packagegroup-magos-graphics"
MAGOS_GRAPHICS_jetson-tx1 = "packagegroup-magos-graphics nvgstplayer nvgstcapture gstreamer1.0-plugins-bad-faad"

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

EXTRA_IMAGECMD_append_ext4 = " -O ^flex_bg,^metadata_csum,^64bit,^extra_isize"
SDCARD_GENERATION_COMMAND_jetson-tx1 = "generate_jetson_sdcard"

generate_jetson_sdcard() {
    SDCARD_SIZE=$(expr ${IMAGE_ROOTFS_ALIGNMENT} \+ ${ROOTFS_SIZE} \+ ${IMAGE_ROOTFS_ALIGNMENT})
    rm -f ${SDCARD}
    dd if=/dev/zero of=${SDCARD} bs=1 count=0 seek=$(expr 1024 \* ${SDCARD_SIZE})
    parted -s ${SDCARD} mklabel gpt
    parted -s ${SDCARD} unit KiB mkpart primary ${IMAGE_ROOTFS_ALIGNMENT} $(expr ${IMAGE_ROOTFS_ALIGNMENT} \+ ${ROOTFS_SIZE})
    parted ${SDCARD} print
    dd if=${SDCARD_ROOTFS} of=${SDCARD} conv=notrunc,fsync seek=1 bs=$(expr ${IMAGE_ROOTFS_ALIGNMENT} \* 1024)
    parted -s ${SDCARD} set 1 boot on
}
