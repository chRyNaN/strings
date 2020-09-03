package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringTypeFileOutput
import com.chrynan.strings.creator.core.StringTypeFileProducer

/**
 * An interface that produces a [StringTypeFileOutput] representation of a Kotlin File (.kt) using the [produce] function.
 *
 * The returned [StringTypeFileOutput] value from the [produce] function can then be used to write to a File.
 */
interface KotlinFileProducer : StringTypeFileProducer<KotlinFileProducerInput> {

    /**
     * Produces a [StringTypeFileOutput] representing a Kotlin file output using the provided [input].
     */
    override fun produce(input: KotlinFileProducerInput): StringTypeFileOutput

    /**
     * A convenience function for calling [produce].
     */
    override operator fun invoke(input: KotlinFileProducerInput): StringTypeFileOutput = produce(input)
}