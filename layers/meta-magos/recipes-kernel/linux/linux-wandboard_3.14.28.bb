# adapted from linux-imx.inc, copyright (C) 2012-2014 O.S. Systems Software LTDA
# Released under the MIT license (see COPYING.MIT for the terms)

# Wandboard branch - based on Freescale's imx_3.14.28_1.0.0_ga


require recipes-kernel/linux/linux-wandboard.inc
require recipes-kernel/linux/linux-dtb.inc

SRCBRANCH = "wandboard_imx_3.14.28_1.0.0_ga"
SRCREV ?= "${AUTOREV}"

LINUX_VERSION ?= "3.14.28"
PV = "${LINUX_VERSION}+git${SRCPV}"

DEPENDS += "lzop-native bc-native"

LOCALVERSION = "_1.0.0-wandboard"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${LINUX_VERSION}:"

COMPATIBLE_MACHINE = "(wandboard)"
