package com.chrynan.strings.accessor

import com.chrynan.strings.core.*

object Strings : StringAccessor,
    StringReviser {

    lateinit var accessor: StringAccessor

    lateinit var reviser: StringReviser

    override fun getStaticString(resourceID: StaticStringResourceID, locale: String): String =
        accessor.getStaticString(resourceID = resourceID, locale = locale)

    override fun getDynamicString(resourceID: DynamicStringResourceID, locale: String, vararg arguments: Any): String =
        accessor.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)

    override fun getHtmlString(resourceID: HtmlStringResourceID, locale: String, vararg arguments: Any): String =
        accessor.getHtmlString(resourceID = resourceID, locale = locale, arguments = *arguments)

    override fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String> =
        accessor.getStringArray(resourceID = resourceID, locale = locale)

    override fun getPluralString(
        resourceID: PluralStringResourceID,
        locale: String,
        quantity: Quantity,
        vararg arguments: Any
    ): String = accessor.getPluralString(resourceID = resourceID, locale = locale, quantity = quantity)

    override val updateListeners: MutableSet<StringUpdateListener>
        get() = reviser.updateListeners

    override fun updateStaticString(resourceID: StaticStringResourceID, locale: String, value: String) =
        reviser.updateStaticString(resourceID = resourceID, locale = locale, value = value)

    override fun updateDynamicString(resourceID: DynamicStringResourceID, locale: String, value: String) =
        reviser.updateDynamicString(resourceID = resourceID, locale = locale, value = value)

    override fun updateHtmlString(resourceID: HtmlStringResourceID, locale: String, value: String) =
        reviser.updateHtmlString(resourceID = resourceID, locale = locale, value = value)

    override fun updateStringArray(resourceID: StringArrayResourceID, locale: String, value: Array<String>) =
        reviser.updateStringArray(resourceID = resourceID, locale = locale, value = value)

    override fun updatePluralStrings(
        resourceID: PluralStringResourceID,
        locale: String,
        values: Map<Quantity, String>
    ) = reviser.updatePluralStrings(
        resourceID = resourceID,
        locale = locale,
        values = values
    )
}