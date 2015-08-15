# Common settings for wandboard boards

include conf/machine/include/imx-base.inc
include conf/machine/include/tune-cortexa9.inc

SERIAL_CONSOLE = "115200 ttymxc0"

MACHINE_FEATURES += "pci touchscreen"

KERNEL_IMAGETYPE = "zImage"

UBOOT_MACHINE = "wandboard_config"
