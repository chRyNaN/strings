package com.chrynan.resources

interface StringAccessor {

    fun getString(resourceID: StringResourceID, locale: Locale = Locale.default): kotlin.String

    fun getDynamicString(
        resourceID: StringResourceID,
        locale: Locale = Locale.default,
        vararg arguments: Any
    ): kotlin.String

    fun getHtmlString(resourceID: StringResourceID, locale: Locale = Locale.default): kotlin.String

    fun getStringArray(resourceID: StringResourceID, locale: Locale = Locale.default): Array<kotlin.String>

    fun getPluralString(
        resourceID: StringResourceID,
        locale: Locale = Locale.default,
        quantity: Quantity,
        vararg arguments: Any
    ): kotlin.String
}