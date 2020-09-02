package com.chrynan.strings.core

/**
 * Represents an argument in a Dynamic Styled [String]. An argument is something that can be changed
 * when the [String] is being retrieved.
 *
 * @property [index] The argument index. For instance, if this is the first argument in the [String],
 * then the index value would be 0.
 * @property [number] The index of the first instance of this argument name. If there are multiple
 * of the same argument in the [String], then this value will always equal the first index of the
 * argument.
 * @property [value] The unformatted value in the [String], including the argument name, type,
 * prefix, and suffix.
 * @property [formattedValue] The same as the [value] without the prefix and suffix.
 * @property [range] The index range where this argument resides in the input [String].
 * @property [name] The name of this argument.
 * @property [type] The type of this argument.
 */
data class StringArgument(
    val index: Int,
    val number: Int,
    val value: String,
    val formattedValue: String,
    val range: IntRange,
    val name: String,
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
        abstract val kotlinTypeName: kotlin.String

        fun matchesTypeName(typeName: kotlin.String, ignoreCase: Boolean = true): Boolean =
            if (ignoreCase) {
                typeNames.firstOrNull { it.toLowerCase() == typeName.toLowerCase() } != null
            } else {
                typeNames.contains(typeName)
            }

        object Any : Type() {

            override val typeNames: List<kotlin.String> = listOf("Any", "a")
            override val kotlinTypeName: kotlin.String = "Any"
        }

        object Int : Type() {

            override val typeNames: List<kotlin.String> = listOf("Int", "i")
            override val kotlinTypeName: kotlin.String = "Int"
        }

        object Long : Type() {

            override val typeNames: List<kotlin.String> = listOf("Long", "l")
            override val kotlinTypeName: kotlin.String = "Long"
        }

        object Float : Type() {

            override val typeNames: List<kotlin.String> = listOf("Float", "f")
            override val kotlinTypeName: kotlin.String = "Float"
        }

        object Double : Type() {

            override val typeNames: List<kotlin.String> = listOf("Double", "d")
            override val kotlinTypeName: kotlin.String = "Double"
        }

        object Char : Type() {

            override val typeNames: List<kotlin.String> = listOf("Char", "c")
            override val kotlinTypeName: kotlin.String = "Char"
        }

        object String : Type() {

            override val typeNames: List<kotlin.String> = listOf("String", "s")
            override val kotlinTypeName: kotlin.String = "String"
        }

        data class Custom(val fullName: kotlin.String) : Type() {

            override val typeNames: List<kotlin.String> = listOf(fullName)
            override val kotlinTypeName: kotlin.String = fullName
        }
    }
}