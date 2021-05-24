KBRANCH ?= "v5.12/standard/base"

require recipes-kernel/linux/linux-yocto.inc

# board specific branches
KBRANCH_qemuarm  ?= "v5.12/standard/arm-versatile-926ejs"
KBRANCH_qemuarm64 ?= "v5.12/standard/qemuarm64"
KBRANCH_qemumips ?= "v5.12/standard/mti-malta32"
KBRANCH_qemuppc  ?= "v5.12/standard/qemuppc"
KBRANCH_qemuriscv64  ?= "v5.12/standard/base"
KBRANCH_qemuriscv32  ?= "v5.12/standard/base"
KBRANCH_qemux86  ?= "v5.12/standard/base"
KBRANCH_qemux86-64 ?= "v5.12/standard/base"
KBRANCH_qemumips64 ?= "v5.12/standard/mti-malta64"

SRCREV_machine_qemuarm ?= "f0da191751beadf9ecb52d24f3bb18f72135deaa"
SRCREV_machine_qemuarm64 ?= "08d8e7c1c18824a3df5c2276c1adcfb5f45a8c5d"
SRCREV_machine_qemumips ?= "d6bc09b094c74592f290b9fbc214874dbab036dd"
SRCREV_machine_qemuppc ?= "08d8e7c1c18824a3df5c2276c1adcfb5f45a8c5d"
SRCREV_machine_qemuriscv64 ?= "08d8e7c1c18824a3df5c2276c1adcfb5f45a8c5d"
SRCREV_machine_qemuriscv32 ?= "08d8e7c1c18824a3df5c2276c1adcfb5f45a8c5d"
SRCREV_machine_qemux86 ?= "08d8e7c1c18824a3df5c2276c1adcfb5f45a8c5d"
SRCREV_machine_qemux86-64 ?= "08d8e7c1c18824a3df5c2276c1adcfb5f45a8c5d"
SRCREV_machine_qemumips64 ?= "4ed37e1f97f1cf6a2c043b1c6ec65b32671707fb"
SRCREV_machine ?= "08d8e7c1c18824a3df5c2276c1adcfb5f45a8c5d"
SRCREV_meta ?= "0e56e5bcbf6af1bb5ae42a25ada73ed6d9ec1234"

# remap qemuarm to qemuarma15 for the 5.8 kernel
# KMACHINE_qemuarm ?= "qemuarma15"

SRC_URI = "git://git.yoctoproject.org/linux-yocto-dev.git;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-5.10;destsuffix=${KMETA}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "5.12.5"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"
DEPENDS += "gmp-native"

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "1"

KERNEL_DEVICETREE_qemuarmv5 = "versatile-pb.dtb"

COMPATIBLE_MACHINE = "qemuarm|qemuarmv5|qemuarm64|qemux86|qemuppc|qemuppc64|qemumips|qemumips64|qemux86-64|qemuriscv64|qemuriscv32"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append_qemuall=" cfg/virtio.scc features/drm-bochs/drm-bochs.scc"
KERNEL_FEATURES_append_qemux86=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append_qemux86-64=" cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "", d)}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/scsi/scsi-debug.scc", "", d)}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("DISTRO_FEATURES", "ptest", " features/gpio/mockup.scc", "", d)}"
