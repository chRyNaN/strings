package com.chrynan.strings

interface StringAccessor {

    fun getString(resourceID: StringResourceID, locale: String = Locale.default): String

    fun getDynamicString(
        resourceID: DynamicStringResourceID,
        locale: String = Locale.default,
        vararg arguments: Any
    ): String

    fun getHtmlString(resourceID: HtmlStringResourceID, locale: String = Locale.default): String

    fun getStringArray(resourceID: StringArrayResourceID, locale: String = Locale.default): Array<String>

    fun getPluralString(
        resourceID: PluralStringResourceID,
        locale: String = Locale.default,
        quantity: Quantity,
        vararg arguments: Any
    ): String
}