package com.chrynan.strings.core

enum class Quantity {

    ZERO,
    ONE,
    TWO,
    FEW,
    MANY,
    OTHER;

    companion object {

        fun fromName(name: String): Quantity? =
            values().firstOrNull { it.name.toLowerCase() == name.toLowerCase() }
    }
}