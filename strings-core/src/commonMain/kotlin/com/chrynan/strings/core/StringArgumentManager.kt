package com.chrynan.strings.core

interface StringArgumentManager : StringArgumentParser,
    StringArgumentFormatter {

    fun parseAndFormat(input: String, vararg arguments: Any): String {
        val result = parse(input = input)

        val values = result.arguments
            .distinctBy { it.number }
            .sortedBy { it.number }
            .mapIndexed { index, argument ->
                StringArgumentFormatter.Value(
                    argument = argument,
                    output = arguments[index].toString()
                )
            }

        return format(input = input, values = values)
    }
}