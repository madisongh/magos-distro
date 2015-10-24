SUMMARY = "Base packagegroup for MagOS images"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = " \
        packagegroup-core-boot \
        packagegroup-base-extended \
        termutils \
        wireless-tools \
        wpa-supplicant \
"
