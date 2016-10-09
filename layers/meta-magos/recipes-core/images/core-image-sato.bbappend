IMAGE_FSTYPES = "sdcard"
IMAGE_FSTYPES_append_jetson-tx1 = " tegraflash"

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
