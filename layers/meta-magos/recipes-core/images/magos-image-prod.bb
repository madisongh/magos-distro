SUMMARY = "MagOS image for production"

LICENSE = "MIT"

IMAGE_FEATURES += "ssh-server-dropbear package-management hwcodecs"

MAGOS_GRAPHICS ?= "packagegroup-magos-graphics"
MAGOS_GRAPHICS_jetson-tx1 = "packagegroup-magos-graphics nvgstplayer nvgstcapture gstreamer1.0-plugins-bad-faad"
MAGOS_GRAPHICS_jetson-tx2 = "packagegroup-magos-graphics nvgstplayer nvgstcapture gstreamer1.0-plugins-bad-faad"

IMAGE_INSTALL = " \
    packagegroup-magos-base \
    packagegroup-magos-mmctools \
    packagegroup-core-full-cmdline \
    ${MAGOS_GRAPHICS} \
"

IMAGE_LINGUAS = " "
IMAGE_FSTYPES_jetson-tx1 = "tegraflash"
IMAGE_FSTYPES_jetson-tx2 = "tegraflash"

generate_imx_sdcard_append() {
    parted -s ${SDCARD} set 1 boot on
}
_generate_boot_image_append() {
    mcopy -i ${WORKDIR}/boot.img -s ${IMAGE_ROOTFS}/boot/extlinux ::/
}
inherit core-image
