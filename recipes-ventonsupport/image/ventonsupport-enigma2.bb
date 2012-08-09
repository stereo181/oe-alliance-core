DESCRIPTION = "Merge machine and distro options to create a enigma2 machine task/package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PV = "1.0"
PR = "r2"

inherit task

DEPENDS = "enigma2-pliplugins ventonsupport-feeds"

RRECOMMENDS = "\
	enigma2-plugin-extensions-autotimer \
	enigma2-plugin-extensions-epgsearch \
	enigma2-plugin-extensions-imdb \
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-settings-default-ventonsupport \
	enigma2-plugin-picons-default-ventonsupport \
	enigma2-plugin-systemplugins-ini-fansetup \
	enigma2-plugin-systemplugins-ini-frontpanelupgrade \
	enigma2-plugin-extensions-inimytube \ 
	\
	${@base_contains("MACHINE_FEATURES", "blindscan-dvbs", "enigma2-plugin-systemplugins-blindscan" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "blindscan-dvbc", "virtual/blindscan-dvbc" , "", d)} \
	"
