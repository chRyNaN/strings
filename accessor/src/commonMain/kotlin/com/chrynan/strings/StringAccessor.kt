package com.chrynan.strings

interface StringAccessor {

    fun getString(resourceID: StringResourceID, locale: Locale = Locale.default): kotlin.String

    fun getDynamicString(
        resourceID: DynamicStringResourceID,
        locale: Locale = Locale.default,
        vararg arguments: Any
    ): kotlin.String

    fun getHtmlString(resourceID: HtmlStringResourceID, locale: Locale = Locale.default): kotlin.String

    fun getStringArray(resourceID: StringArrayResourceID, locale: Locale = Locale.default): Array<kotlin.String>

    fun getPluralString(
        resourceID: PluralStringResourceID,
        locale: Locale = Locale.default,
        quantity: Quantity,
        vararg arguments: Any
    ): kotlin.String
}