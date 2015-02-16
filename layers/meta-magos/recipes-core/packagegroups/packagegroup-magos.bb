SUMMARY = "Package groups for MagOS images"
LICENSE = "MIT"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = " \
        packagegroup-magos-base \
        packagegroup-magos-mmctools \
        packagegroup-magos-support \
	packagegroup-magos-buildtools \
"

RDEPENDS_packagegroup-magos-base = " \
        packagegroup-core-boot \
        packagegroup-base-extended \
"

RDEPENDS_packagegroup-magos-mmctools = " \
        mmc-utils \
        mtd-utils \
	packagegroup-base-vfat \
        parted \
"

RDEPENDS_packagegroup-magos-support = " \
        lsb \
        lsbinitscripts \
	openssh \
	tzcode \
"

RDEPENDS_packagegroup-magos-buildtools = "\
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
