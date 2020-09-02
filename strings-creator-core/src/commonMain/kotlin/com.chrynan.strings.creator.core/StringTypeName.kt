package com.chrynan.strings.creator.core

/**
 * An enum class representing the different supported String Types and their respective names.
 *
 * There are currently five supported String Types: [STATIC], [DYNAMIC], [HTML], [ARRAY], and
 * [PLURALS]. This represents the names for each of those supported String Types but does not
 * represent an instance of a String Type. For an instance of a String Type, refer to the
 * [StringType] class.
 *
 * This class is used by the [StringType] class as a type identifier.
 */
enum class StringTypeName(val value: String) {

    STATIC(value = "static"),
    DYNAMIC(value = "dynamic"),
    HTML(value = "html"),
    ARRAY(value = "array"),
    PLURALS(value = "plurals");

    companion object {

        /**
         * Retrieves the [StringTypeName] from the provided [name], or null if there is no match.
         *
         * Note: The comparison is not case-sensitive.
         */
        fun fromName(name: String): StringTypeName? =
            values().firstOrNull { it.value.toLowerCase() == name.toLowerCase() }
    }
}