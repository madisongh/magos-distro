do_install_append() {
    sed -i -e's,^Version: 1\.0\.0,Version: 9.0.0,' ${D}${libdir}/pkgconfig/wayland-egl.pc
}
