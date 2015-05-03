SUMMARY = "Support packages for MagOS"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = " \
        lsb \
        lsbinitscripts \
	openssh \
	tzcode \
	usbutils \
"
