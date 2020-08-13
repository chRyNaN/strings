package com.chrynan.strings.creator.core

import com.chrynan.strings.core.Locale
import com.chrynan.strings.core.Quantity

sealed class StringType {

    abstract val name: String
    abstract val locale: String

    data class StaticString(
        override val name: String,
        override val locale: String = Locale.default,
        val value: String
    ) : StringType()

    data class DynamicString(
        override val name: String,
        override val locale: String = Locale.default,
        val value: String
    ) : StringType()

    data class HtmlString(
        override val name: String,
        override val locale: String = Locale.default,
        val value: String
    ) : StringType()

    data class StringArray(
        override val name: String,
        override val locale: String = Locale.default,
        val values: List<String> = emptyList()
    ) : StringType()

    data class StringPlurals(
        override val name: String,
        override val locale: String = Locale.default,
        val values: List<PluralItem> = emptyList()
    ) : StringType() {

        data class PluralItem(
            val value: String,
            val quantity: Quantity
        )
    }
}