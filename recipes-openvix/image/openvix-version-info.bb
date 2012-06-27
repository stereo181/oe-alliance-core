DESCRIPTION = "ViX version info"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "ViX team"

require conf/license/license-gplv2.inc

PV = "${IMAGE_VERSION}"
PR = "r${DATETIME}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

URL_vuuno = "http://www.vuplus-support.co.uk"
URL_vuultimo = "http://www.vuplus-support.co.uk"
URL_vusolo = "http://www.vuplus-support.co.uk"
URL_vuduo = "http://www.vuplus-support.co.uk"
URL_et5x00 = "http://www.xtrend-support.co.uk"
URL_et6x00 = "http://www.xtrend-support.co.uk"
URL_et9x00 = "http://www.xtrend-support.co.uk"
URL_tmtwin = "http://www.technomate-support.co.uk"
URL_odinm9 = "http://www.odin-support.co.uk"
URL_gb800solo = "http://www.gigablue-support.co.uk"
URL_gb800se = "http://www.gigablue-support.co.uk"
URL_gb800ue = "http://www.gigablue-support.co.uk"
URL_gbquad = "http://www.gigablue-support.co.uk"
URL_venton-hdx = "http://www.gigablue-support.co.uk"

SRC_URI = "file://releasenotes"

S = "${WORKDIR}"

inherit autotools

PACKAGES = "${PN}"

do_install() {
			if [ "${DISTRO_TYPE}" = "experimental" ] ; then
				BUILDTYPE="1"
			else
				BUILDTYPE="0"
			fi
			install -d ${D}/etc
			# generate /etc/image-version
			echo "box_type=${MACHINE}" > ${D}/etc/image-version
			echo "build_type=${BUILDTYPE}" >> ${D}/etc/image-version
			echo "version=${IMAGE_VERSION}" >> ${D}/etc/image-version
			echo "build=${BUILD_VERSION}" >> ${D}/etc/image-version
			if [ "${MACHINE}" = "vusolo" -o "${MACHINE}" = "vuduo" -o "${MACHINE}" = "vuuno" -o "${MACHINE}" = "vuultimo" ]; then
				DRIVERS=`grep SRCDATE_vusolo ${LAYERDIR}/recipes-bsp/vuplus/vuplus-dvb-modules-${MACHINE}.bb | cut -b 19-26`
			elif [ "${MACHINE}" = "et5x00" -o "${MACHINE}" = "et6x00" -o "${MACHINE}" = "et9x00" ]; then
				DRIVERS=`grep "SRCDATE = " ${LAYERDIR}/recipes-bsp/etxx00/et-dvb-modules-${MACHINE}.bb | cut -b 12-19`
			elif [ "${MACHINE}" = "odinm9" ]; then
				DRIVERS=`grep "SRCDATE = " ${LAYERDIR}/recipes-bsp/odin/odin-dvb-modules-${MACHINE}.bb | cut -b 12-19`
			elif [ "${MACHINE}" = "tmtwin" ]; then
				DRIVERS=`grep "SRCDATE = " ${LAYERDIR}/recipes-bsp/technomate/tm-dvb-modules.bb | cut -b 12-21`
			elif [ "${MACHINE}" = "gb800solo" -o "${MACHINE}" = "gb800se" -o "${MACHINE}" = "gb800ue" -o "${MACHINE}" = "gbquad" ]; then
				DRIVERS=`grep "SRCDATE = " ${LAYERDIR}/recipes-bsp/gigablue/gigablue-dvb-modules-${MACHINE}.bb | cut -b 12-19`
			elif [ "${MACHINE}" = "venton-hdx" ]; then
				DRIVERS=`grep "SRCDATE = " ${LAYERDIR}/recipes-bsp/venton/venton-dvb-modules-hdx.bb | cut -b 12-19`				
			else
				DRIVERS='N/A'
			fi
			echo "drivers=${DRIVERS}" >> ${D}/etc/image-version
			echo "date=${DATETIME}" >> ${D}/etc/image-version
			echo "comment=ViX" >> ${D}/etc/image-version
			echo "target=9" >> ${D}/etc/image-version
			echo "creator=Team ViX" >> ${D}/etc/image-version
			echo "url=${URL}" >> ${D}/etc/image-version
			echo "catalog=${URL}" >> ${D}/etc/image-version

			install -m 0644 ${WORKDIR}/releasenotes ${D}/etc/releasenotes
}

FILES_${PN} = "/etc/image-version /etc/releasenotes"

