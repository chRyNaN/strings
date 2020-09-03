package com.chrynan.strings.plugin.gradle.json

open class JsonStringInputExtension {

    var inputPaths: List<String> = emptyList()
    var outputPath: String? = null
    var outputPackageName: String? = null
    var fileNameLocaleSeparator: String? = null
    var androidResourcePath: String? = null
    var generateExtensionFunctions: Boolean = true
}