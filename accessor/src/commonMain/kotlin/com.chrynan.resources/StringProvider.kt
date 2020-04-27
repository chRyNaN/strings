package com.chrynan.resources

import kotlin.String

object StringProvider : StringAccessor {

    lateinit var accessor: StringAccessor

    override fun getString(resourceID: StringResourceID, locale: Locale): String =
        accessor.getString(resourceID = resourceID, locale = locale)

    override fun getDynamicString(resourceID: StringResourceID, locale: Locale, vararg arguments: Any): String =
        accessor.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)

    override fun getHtmlString(resourceID: StringResourceID, locale: Locale): String =
        accessor.getHtmlString(resourceID = resourceID, locale = locale)

    override fun getStringArray(resourceID: StringResourceID, locale: Locale): Array<String> =
        accessor.getStringArray(resourceID = resourceID, locale = locale)

    override fun getPluralString(
        resourceID: StringResourceID,
        locale: Locale,
        quantity: Quantity,
        vararg arguments: Any
    ): String =
        accessor.getPluralString(resourceID = resourceID, locale = locale, quantity = quantity, arguments = *arguments)
}