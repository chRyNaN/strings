package com.chrynan.strings.accessor

import com.chrynan.strings.*
import kotlin.String

object Strings : StringAccessor {

    lateinit var accessor: StringAccessor

    override fun getStaticString(resourceID: StaticStringResourceID, locale: String): String =
        accessor.getStaticString(resourceID = resourceID, locale = locale)

    override fun getDynamicString(resourceID: DynamicStringResourceID, locale: String, vararg arguments: Any): String =
        accessor.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)

    override fun getHtmlString(resourceID: HtmlStringResourceID, locale: String): String =
        accessor.getHtmlString(resourceID = resourceID, locale = locale)

    override fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String> =
        accessor.getStringArray(resourceID = resourceID, locale = locale)

    override fun getPluralString(
        resourceID: PluralStringResourceID,
        locale: String,
        quantity: Quantity,
        vararg arguments: Any
    ): String =
        accessor.getPluralString(resourceID = resourceID, locale = locale, quantity = quantity, arguments = *arguments)
}