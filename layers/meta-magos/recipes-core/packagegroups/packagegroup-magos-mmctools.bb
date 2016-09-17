SUMMARY = "MMC tools packagegroup for MagOS images"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = " \
        mmc-utils \
        mtd-utils \
        parted \
"
