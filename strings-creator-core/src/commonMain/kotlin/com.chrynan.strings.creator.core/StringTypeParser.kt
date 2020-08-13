package com.chrynan.strings.creator.core

interface StringTypeParser<T> {

    fun parse(input: T): List<StringType>

    operator fun invoke(input: T): List<StringType> = parse(input)
}