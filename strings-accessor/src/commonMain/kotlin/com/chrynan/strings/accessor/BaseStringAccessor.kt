package com.chrynan.strings.accessor

import com.chrynan.strings.core.*

abstract class BaseStringAccessor : StringAccessor {

    abstract val repository: StringRepository
    abstract val argumentManager: StringArgumentManager
    abstract val computedStringCache: ComputedStringCache

    override fun getStaticString(resourceID: StaticStringResourceID, locale: String): String =
        repository.getStringValue(resourceID = resourceID, locale = locale)

    override fun getDynamicString(resourceID: DynamicStringResourceID, locale: String, vararg arguments: Any): String {
        val cacheKey = ComputedStringCache.Key(
            resourceID = resourceID,
            locale = locale,
            arguments = arguments.toList()
        )

        val cacheValue = computedStringCache[cacheKey]

        if (cacheValue != null) return cacheValue

        val string = repository.getStringValue(resourceID = resourceID, locale = locale)

        val formattedOutput = argumentManager.parseAndFormat(input = string, arguments = *arguments)

        computedStringCache[cacheKey] = formattedOutput

        return formattedOutput
    }

    override fun getHtmlString(resourceID: HtmlStringResourceID, locale: String, vararg arguments: Any): String {
        val cacheKey = ComputedStringCache.Key(
            resourceID = resourceID,
            locale = locale,
            arguments = arguments.toList()
        )

        val cacheValue = computedStringCache[cacheKey]

        if (cacheValue != null) return cacheValue

        val string = repository.getStringValue(resourceID = resourceID, locale = locale)

        val formattedOutput = argumentManager.parseAndFormat(input = string, arguments = *arguments)

        computedStringCache[cacheKey] = formattedOutput

        return formattedOutput
    }

    override fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String> =
        repository.getStringArray(resourceID = resourceID, locale = locale)

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

        val string = repository.getPluralStringValue(resourceID = resourceID, locale = locale, quantity = quantity)

        val formattedOutput = argumentManager.parseAndFormat(input = string, arguments = *arguments)

        computedStringCache[cacheKey] = formattedOutput

        return formattedOutput
    }
}