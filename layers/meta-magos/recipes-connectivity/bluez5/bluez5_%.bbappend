FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"
SRC_URI += "file://bluetooth.sh"

inherit update-rc.d

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/bluetooth.sh ${D}${sysconfdir}/init.d
    update-rc.d -r ${D} bluetooth.sh start 06 S .
}

INITSCRIPT_NAME = "bluetooth.sh"

FILES_${PN} += "${sysconfdir}"
