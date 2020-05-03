package com.chrynan.strings

import kotlin.String

abstract class BaseStringAccessor(
    private val repo: StringRepository,
    private val parser: StringArgumentParser,
    private val formatter: StringArgumentFormatter,
    private val computedStringCache: ComputedStringCache = MapComputedStringCache()
) : StringAccessor {

    override fun getStaticString(resourceID: StaticStringResourceID, locale: String): String =
        repo.getStringValue(resourceID = resourceID, locale = locale)

    override fun getDynamicString(resourceID: DynamicStringResourceID, locale: String, vararg arguments: Any): String {
        val cacheKey = ComputedStringCache.Key(resourceID = resourceID, locale = locale, arguments = arguments.toList())

        val cacheValue = computedStringCache[cacheKey]

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

        val formattedOutput = formatter.format(input = string, values = values)

        computedStringCache[cacheKey] = formattedOutput

        return formattedOutput
    }

    override fun getHtmlString(resourceID: HtmlStringResourceID, locale: String): String =
        repo.getStringValue(resourceID = resourceID, locale = locale)

    override fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String> =
        repo.getStringArray(resourceID = resourceID, locale = locale)

    override fun getPluralString(
        resourceID: PluralStringResourceID,
        locale: String,
        quantity: Quantity,
        vararg arguments: Any
    ): String {
        val cacheKey =
            ComputedStringCache.Key(
                resourceID = resourceID,
                locale = locale,
                quantity = quantity,
                arguments = arguments.toList()
            )

        val cacheValue = computedStringCache[cacheKey]

        if (cacheValue != null) return cacheValue

        val string = repo.getPluralStringValue(resourceID = resourceID, locale = locale, quantity = quantity)

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

        val formattedOutput = formatter.format(input = string, values = values)

        computedStringCache[cacheKey] = formattedOutput

        return formattedOutput
    }
}