package com.chrynan.strings.creator.core

interface StringTypeProducer<T> {

    fun produce(types: List<StringType>): T

    operator fun invoke(types: List<StringType>): T = produce(types)
}