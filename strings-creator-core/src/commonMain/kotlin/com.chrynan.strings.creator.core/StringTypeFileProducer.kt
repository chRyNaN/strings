package com.chrynan.strings.creator.core

/**
 * An interface that produces a [StringTypeFileOutput] representation of a File using the [produce] function.
 *
 * The returned [StringTypeFileOutput] value from the [produce] function can then be used to write to a File.
 */
interface StringTypeFileProducer<T> {

    /**
     * Produces a [StringTypeFileOutput] representing a file output using the provided [input].
     */
    fun produce(input: T): StringTypeFileOutput

    /**
     * A convenience function for calling [produce].
     */
    operator fun invoke(input: T): StringTypeFileOutput = produce(input)
}