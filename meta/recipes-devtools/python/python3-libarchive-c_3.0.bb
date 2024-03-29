SUMMARY = "Python interface to libarchive"
DESCRIPTION = "A Python interface to libarchive. It uses the standard ctypes module to \
    dynamically load and access the C library."
HOMEPAGE = "https://github.com/Changaco/python-libarchive-c"
LICENSE = "CC0-1.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=bcab380227a83bc147350b40a81e6ffc"

PYPI_PACKAGE = "libarchive-c"

inherit pypi setuptools3

SRC_URI[md5sum] = "920885f5910aa8245e30896996e0415b"
SRC_URI[sha256sum] = "6f12fa9cf0e44579e5f45bbf11aa455a930fbfdb13ea0ce3c3dfe7b9b9a452ba"

RDEPENDS_${PN} += "\
  libarchive \
  ${PYTHON_PN}-ctypes \
  ${PYTHON_PN}-mmap \
"

BBCLASSEXTEND = "native"
