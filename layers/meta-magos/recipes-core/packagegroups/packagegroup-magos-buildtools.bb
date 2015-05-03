SUMMARY = "Build tools package group for MagOS"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
    packagegroup-core-buildessential \
    coreutils \
    diffutils \
    intltool \
    perl-module-re \
    perl-module-text-wrap \
    findutils \
    quilt \
    less \
    ldd \
    file \
"
