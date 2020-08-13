package com.chrynan.strings

interface StringArgumentParser {

    fun parse(input: String): Result

    data class Result(
        val input: String,
        val arguments: List<Argument> = emptyList()
    )

    data class Argument(
        val number: Int,
        val value: String,
        val startIndexInclusive: Int,
        val endIndexExclusive: Int,
        val type: Type
    ) {

        enum class Type {

            INT,
            FLOAT,
            DOUBLE,
            CHAR,
            STRING
        }
    }
}