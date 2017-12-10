do_install_append() {
    rm -f ${D}${bindir}/gflags_completions.sh
    rmdir ${D}${bindir}
}
PACKAGES_remove = "${PN}-bash-completion"
