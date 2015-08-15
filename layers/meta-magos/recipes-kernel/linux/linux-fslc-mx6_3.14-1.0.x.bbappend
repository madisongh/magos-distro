FILESEXTRAPATHS_prepend := "${THISDIR}/${BP}:"
WANDBOARD_GITHUB_MIRROR = "git://github.com/madisongh/linux"
SRC_URI = "${WANDBOARD_GITHUB_MIRROR};branch=${SRCBRANCH} \
	   file://defconfig"
PV = "3.14-1.0.x+git${SRCPV}"
SRCREV = "${AUTOREV}"
SRCBRANCH = "wandboard_3.14-1.0.x-mx6"
COMPATIBLE_MACHINE="(wandboard-.*)"
LOCALVERSION = "-wandboard"
