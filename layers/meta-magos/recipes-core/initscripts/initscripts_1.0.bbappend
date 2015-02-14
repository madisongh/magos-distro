do_install_append() {
	update-rc.d -r ${D} -f devpts.sh remove
	update-rc.d -r ${D} devpts.sh start 06 S .
}
