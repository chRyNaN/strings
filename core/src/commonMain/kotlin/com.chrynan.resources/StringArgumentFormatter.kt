package com.chrynan.resources

interface StringArgumentFormatter {

    fun format(input: String, vararg values: Value): String

    data class Value(
        val inputValue: String,
        val outputValue: String
    )
}