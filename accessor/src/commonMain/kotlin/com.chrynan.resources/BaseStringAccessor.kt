package com.chrynan.resources

import kotlin.String

abstract class BaseStringAccessor(
    private val repo: StringRepo,
    private val parser: StringArgumentParser,
    private val formatter: StringArgumentFormatter
) : StringAccessor {

    private val computedValueCache = mutableMapOf<CacheKey, String>()

    override fun getString(resourceID: StringResourceID, locale: Locale): String =
        repo.getStringValue(resourceID = resourceID, locale = locale)

    override fun getDynamicString(resourceID: DynamicStringResourceID, locale: Locale, vararg arguments: Any): String {
        val cacheKey = CacheKey(resourceID = resourceID, locale = locale, arguments = arguments.toList())

        val cacheValue = computedValueCache[cacheKey]

        if (cacheValue != null) return cacheValue

        val string = repo.getStringValue(resourceID = resourceID, locale = locale)

        val result = parser.parse(input = string)

        val values = result.arguments
            .distinctBy { it.number }
            .sortedBy { it.number }
            .mapIndexed { index, argument ->
                StringArgumentFormatter.Value(
                    inputValue = argument.value,
                    outputValue = arguments[index].toString()
                )
            }

        return formatter.format(input = string, values = values)
    }

    override fun getHtmlString(resourceID: HtmlStringResourceID, locale: Locale): String =
        repo.getStringValue(resourceID = resourceID, locale = locale)

    override fun getStringArray(resourceID: StringArrayResourceID, locale: Locale): Array<String> =
        repo.getStringArray(resourceID = resourceID, locale = locale)

    override fun getPluralString(
        resourceID: PluralStringResourceID,
        locale: Locale,
        quantity: Quantity,
        vararg arguments: Any
    ): String {
        val string = repo.getStringValue(resourceID = resourceID, locale = locale)

        TODO("Not yet implemented")
    }

    private data class CacheKey(
        val resourceID: ResourceID,
        val locale: Locale,
        val arguments: List<Any>
    )
}