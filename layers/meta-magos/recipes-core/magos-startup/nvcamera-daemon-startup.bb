DESCRIPTION = "Startup script for nvcamera"

COMPATIBLE_MACHINE = "(jetson-tx1)"
PACKAGE_ARCH = "${MACHINE_ARCH}"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "file://nvcamera-daemon.init"

S = "${WORKDIR}"

inherit update-rc.d

do_compile[noexec] = "1"

do_install() {
  install -d ${D}${sysconfdir}/init.d
  install -m 0755 ${S}/nvcamera-daemon.init ${D}${sysconfdir}/init.d/nvcamera-daemon
}

INITSCRIPT_NAME = "nvcamera-daemon"
INITSCRIPT_PARAMS = "defaults 70 30"

PACKAGES = "${PN}"