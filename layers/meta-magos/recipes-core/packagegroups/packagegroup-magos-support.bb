SUMMARY = "Support packages for MagOS"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = " \
        bluez5-noinst-tools \
        lsb \
        lsbinitscripts \
	openssh \
	strace \
	tzcode \
	usbutils \
	v4l-utils \
"
