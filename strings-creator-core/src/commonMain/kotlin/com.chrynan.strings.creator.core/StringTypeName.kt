package com.chrynan.strings.creator.core

enum class StringTypeName(val value: String) {

    STATIC(value = "static"),
    DYNAMIC(value = "dynamic"),
    HTML(value = "html"),
    ARRAY(value = "array"),
    PLURALS(value = "plurals");

    companion object {

        fun fromName(name: String): StringTypeName? =
            values().firstOrNull { it.value.toLowerCase() == name.toLowerCase() }
    }
}