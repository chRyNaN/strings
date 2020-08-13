package com.chrynan.strings.plugin.core

import de.jensklingenberg.mpapt.model.Element

fun Element.fileNameAndLocation(): FileNameAndLocation =
    FileNameAndLocation(
        path = path,
        packageName = pack,
        fileName = simpleName
    )