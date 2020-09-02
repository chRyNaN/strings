package com.chrynan.strings.creator.core

/**
 * A class that takes in an input of type [T], in the [parse] function, and parses it into
 * a [List] of [StringType]s for the output.
 */
interface StringTypeParser<T> {

    /**
     * Parses the provided [input] into a [List] of [StringType]s.
     */
    fun parse(input: T): List<StringType>

    /**
     * A convenience operator function for calling [parse].
     */
    operator fun invoke(input: T): List<StringType> = parse(input)
}