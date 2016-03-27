SUMMARY = "Base packagegroup for MagOS images"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

CAMSTART = ""
CAMSTART_jetson-tx1 = "${@['nvcamera-daemon-startup', ''][(d.getVar('JETSON_USE_V4L2', True) or '') == '1']}"

RDEPENDS_${PN} = " \
        packagegroup-core-boot \
        packagegroup-base-extended \
        termutils \
        wireless-tools \
        wpa-supplicant \
        ${CAMSTART} \
"
