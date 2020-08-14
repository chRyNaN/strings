package com.chrynan.strings.creator.output.kotlin

/**
 * An interface that produces a [String] representation of a Kotlin File (.kt) using the [produce] function.
 *
 * The returned [String] value from the [produce] function can then be used to write to a File.
 */
interface KotlinFileProducer {

    fun produce(input: KotlinFileProducerInput): String

    operator fun invoke(input: KotlinFileProducerInput): String = produce(input)
}