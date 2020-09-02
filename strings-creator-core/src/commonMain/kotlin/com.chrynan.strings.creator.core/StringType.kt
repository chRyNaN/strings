package com.chrynan.strings.creator.core

import com.chrynan.strings.core.Locale
import com.chrynan.strings.core.Quantity

/**
 * Represents a supported String Type instance and it's supported data. This is a sealed class with
 * each of the supported String Types inheriting from this sealed class. This class is used to
 * represent all of the supported String Types so that they can be used when generating files or
 * processing the values.
 *
 * @property [name] The name of the String Type.
 * @property [locale] The locale of the String Type.
 * @property [typeName] The [StringTypeName] of the String Type.
 */
sealed class StringType {

    abstract val name: String
    abstract val locale: String
    abstract val typeName: StringTypeName

    data class Static(
        override val name: String,
        override val locale: String = Locale.default,
        val value: String
    ) : StringType() {

        override val typeName: StringTypeName = StringTypeName.STATIC
    }

    data class Dynamic(
        override val name: String,
        override val locale: String = Locale.default,
        val value: String
    ) : StringType() {

        override val typeName: StringTypeName = StringTypeName.DYNAMIC
    }

    data class Html(
        override val name: String,
        override val locale: String = Locale.default,
        val value: String
    ) : StringType() {

        override val typeName: StringTypeName = StringTypeName.HTML
    }

    data class Array(
        override val name: String,
        override val locale: String = Locale.default,
        val values: List<String> = emptyList()
    ) : StringType() {

        override val typeName: StringTypeName = StringTypeName.ARRAY
    }

    data class Plurals(
        override val name: String,
        override val locale: String = Locale.default,
        val values: List<Item> = emptyList()
    ) : StringType() {

        override val typeName: StringTypeName = StringTypeName.PLURALS

        data class Item(
            val value: String,
            val quantity: Quantity
        )
    }
}
