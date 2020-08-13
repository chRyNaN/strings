package com.chrynan.strings.core

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

        sealed class Type {

            object Int : Type()

            object Float : Type()

            object Double : Type()

            object Char : Type()

            object String : Type()

            data class Custom(val fullName: kotlin.String) : Type()
        }
    }
}