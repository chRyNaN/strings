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

            companion object {

                fun fromTypeName(typeName: kotlin.String, ignoreCase: Boolean = true): Type =
                    when {
                        Any.matchesTypeName(typeName, ignoreCase) -> Any
                        Int.matchesTypeName(typeName, ignoreCase) -> Int
                        Long.matchesTypeName(typeName, ignoreCase) -> Long
                        Float.matchesTypeName(typeName, ignoreCase) -> Float
                        Double.matchesTypeName(typeName, ignoreCase) -> Double
                        Char.matchesTypeName(typeName, ignoreCase) -> Char
                        String.matchesTypeName(typeName, ignoreCase) -> String
                        else -> Custom(typeName)
                    }
            }

            abstract val typeNames: List<kotlin.String>

            fun matchesTypeName(typeName: kotlin.String, ignoreCase: Boolean = true): Boolean =
                if (ignoreCase) {
                    typeNames.firstOrNull { it.toLowerCase() == typeName.toLowerCase() } != null
                } else {
                    typeNames.contains(typeName)
                }

            object Any : Type() {

                override val typeNames: List<kotlin.String> = listOf("Any", "a")
            }

            object Int : Type() {

                override val typeNames: List<kotlin.String> = listOf("Int", "i")
            }

            object Long : Type() {

                override val typeNames: List<kotlin.String> = listOf("Long", "l")
            }

            object Float : Type() {

                override val typeNames: List<kotlin.String> = listOf("Float", "f")
            }

            object Double : Type() {

                override val typeNames: List<kotlin.String> = listOf("Double", "d")
            }

            object Char : Type() {

                override val typeNames: List<kotlin.String> = listOf("Char", "c")
            }

            object String : Type() {

                override val typeNames: List<kotlin.String> = listOf("String", "s")
            }

            data class Custom(val fullName: kotlin.String) : Type() {

                override val typeNames: List<kotlin.String> = listOf(fullName)
            }
        }
    }
}