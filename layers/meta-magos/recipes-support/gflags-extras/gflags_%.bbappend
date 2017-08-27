do_install_append() {
    rm -f ${D}${bindir}/gflags_completions.sh
}
PACKAGES_remove = "${PN}-bash-completion"
