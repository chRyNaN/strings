package com.chrynan.strings.core

interface StringArgumentParser {

    fun parse(input: String): Result

    data class Result(
        val input: String,
        val arguments: List<StringArgument> = emptyList()
    )
}