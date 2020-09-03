package com.chrynan.strings.creator.output.kotlin

/**
 * Represents the output result from a [KotlinFileProducer.produce] function.
 *
 * @property [fileName] The name of the Kotlin file to be created. This file name should include
 * the Kotlin file suffix (.kt).
 * @property [fileText] The [String] value of the Kotlin file body. This will be the actual text
 * that will be in the resulting Kotlin file.
 */
data class KotlinFileProducerOutput(
    val fileName: String,
    val fileText: String
)