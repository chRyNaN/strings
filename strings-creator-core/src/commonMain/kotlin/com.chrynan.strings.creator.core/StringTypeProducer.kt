package com.chrynan.strings.creator.core

/**
 * A class that takes in an input of a [List] of [StringType]s, in the [produce] function, and
 * produces an output of type [T] from it.
 */
interface StringTypeProducer<T> {

    /**
     * Produces [T] from the provided [types].
     */
    fun produce(types: List<StringType>): T

    /**
     * A convenience operator function for calling [produce].
     */
    operator fun invoke(types: List<StringType>): T = produce(types)
}