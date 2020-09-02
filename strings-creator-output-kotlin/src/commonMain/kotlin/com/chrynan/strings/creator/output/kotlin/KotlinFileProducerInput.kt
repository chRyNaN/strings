package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType

/**
 * Represents the input to a [KotlinFileProducer]. These values are used to help create the
 * Kotlin file output String returned from the [KotlinFileProducer.produce] function.
 *
 * @property [types] The [StringType]s used to create the Kotlin file output.
 * @property [packageName] The Kotlin package name for the Kotlin file output.
 */
data class KotlinFileProducerInput(
    val types: List<StringType>,
    val packageName: String
)