FILES_${PN}-pam = "${base_libdir}/security"
RRECOMMENDS_${PN} += "${PN}-pam"
RRECOMMENDS_udev += "udev-hwdb"
