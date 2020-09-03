package com.chrynan.strings.creator.core

/**
 * Represents the output result from a [StringTypeFileProducer.produce] function.
 *
 * @property [fileName] The name of the file to be created. This file name should include
 * the file suffix (ex: .kt).
 * @property [fileText] The [String] value of the file body. This will be the actual text
 * that will be in the resulting file.
 */
data class StringTypeFileOutput(
    val fileName: String,
    val fileText: String
)