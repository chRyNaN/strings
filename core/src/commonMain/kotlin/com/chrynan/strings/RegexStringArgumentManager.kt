package com.chrynan.strings

class RegexStringArgumentManager : StringArgumentParser,
    StringArgumentFormatter {

    companion object {

        private val REGEX_STRING = Regex("%[0-9]$[s]")
    }

    override fun parse(input: String): StringArgumentParser.Result {
        // TODO
        return StringArgumentParser.Result(
            input = "",
            arguments = emptyList()
        )
    }

    override fun format(input: String, values: List<StringArgumentFormatter.Value>): String {
        // TODO
        return ""
    }
}