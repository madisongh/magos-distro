LINUX_VERSION = "4.1-1.0.x"
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${LINUX_VERSION}:"
WANDBOARD_GITHUB_MIRROR ?= "git://github.com/madisongh/linux"
SRC_URI = "${WANDBOARD_GITHUB_MIRROR};branch=${SRCBRANCH} \
	   file://defconfig"
PV = "${LINUX_VERSION}+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRCBRANCH = "wandboard_${LINUX_VERSION}-imx"
COMPATIBLE_MACHINE="(wandboard)"
LOCALVERSION = "-wandboard"

do_install_append() {
    install -d ${D}/${KERNEL_IMAGEDEST}/extlinux
    rm -f ${D}/${KERNEL_IMAGEDEST}/extlinux/extlinux.conf
    cat >${D}/${KERNEL_IMAGEDEST}/extlinux/extlinux.conf << EOF
DEFAULT primary
TIMEOUT 30
MENU TITLE Boot Options
LABEL primary
      MENU LABEL primary ${KERNEL_IMAGETYPE}-${KERNEL_VERSION}
      LINUX /${KERNEL_IMAGETYPE}
      FDT /${@d.getVar('KERNEL_DEVICETREE', True).split()[0]}
      APPEND console=ttymxc0,115200 root=/dev/mmcblk2p2 rootwait rw
EOF
}

FILES_kernel-image += "/${KERNEL_IMAGEDEST}/extlinux"

do_preconfigure[dirs] = "${B}"
