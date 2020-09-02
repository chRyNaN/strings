package com.chrynan.strings.core

class RegexStringArgumentManager : StringArgumentParser,
    StringArgumentFormatter {

    companion object {

        private const val ESCAPE_CHAR = '\\'
        private const val ARGUMENT_PREFIX = "\${"
        private const val ARGUMENT_SUFFIX = "}"
        private const val KEY_VALUE_SEPARATOR = ':'

        private val ARGUMENT_REGEX = Regex("\\$\\{[.*]}")
    }

    override fun parse(input: String): StringArgumentParser.Result {
        val numbers = mutableMapOf<String, Int>()

        val arguments = ARGUMENT_REGEX.findAll(input)
            .filter { input.getOrNull(it.range.first - 1) != ESCAPE_CHAR }
            .filter { it.value.contains(KEY_VALUE_SEPARATOR) }
            .mapIndexed { index, item ->
                val formattedValue = item.value.removeSurrounding(prefix = ARGUMENT_PREFIX, suffix = ARGUMENT_SUFFIX)

                var number = numbers[formattedValue]

                if (number == null) {
                    number = index
                    numbers[formattedValue] = number
                }

                val name = formattedValue.substringBefore(KEY_VALUE_SEPARATOR)
                val typeName = formattedValue.substringAfter(KEY_VALUE_SEPARATOR)

                StringArgumentParser.Argument(
                    index = index,
                    number = number,
                    range = item.range,
                    value = item.value,
                    formattedValue = formattedValue,
                    name = name,
                    type = StringArgumentParser.Argument.Type.fromTypeName(typeName)
                )
            }
            .toList()

        return StringArgumentParser.Result(
            input = input,
            arguments = arguments
        )
    }

    override fun format(input: String, values: List<StringArgumentFormatter.Value>): String {
        // TODO
        return ""
    }
}