package com.chrynan.strings.core

interface StringArgumentFormatter {

    fun format(input: String, values: List<Value>): String

    data class Value(
        val argument: StringArgument,
        val output: String
    )
}