package com.chrynan.strings

interface StringArgumentFormatter {

    fun format(input: String, values: List<Value>): String

    data class Value(
        val inputValue: String,
        val outputValue: String
    )
}