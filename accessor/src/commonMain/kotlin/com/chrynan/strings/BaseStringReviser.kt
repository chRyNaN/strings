package com.chrynan.strings

abstract class BaseStringReviser(
    private val repository: MutableStringRepository,
    parser: StringArgumentParser,
    formatter: StringArgumentFormatter,
    private val computedStringCache: ComputedStringCache
) : BaseStringAccessor(
    repository = repository,
    parser = parser,
    formatter = formatter,
    computedStringCache = computedStringCache
), StringReviser {

    override fun updateStaticString(resourceID: StaticStringResourceID, locale: String, value: String) {
        repository.updateStringValue(resourceID = resourceID, locale = locale, value = value)
    }

    override fun updateDynamicString(resourceID: DynamicStringResourceID, locale: String, value: String) {
        repository.updateStringValue(resourceID = resourceID, locale = locale, value = value)

        computedStringCache.entries
            .filter { it.key.resourceID == resourceID && it.key.locale == locale }
            .forEach { computedStringCache[it.key] = null }
    }

    override fun updateHtmlString(resourceID: HtmlStringResourceID, locale: String, value: String) {
        repository.updateStringValue(resourceID = resourceID, locale = locale, value = value)

        computedStringCache.entries
            .filter { it.key.resourceID == resourceID && it.key.locale == locale }
            .forEach { computedStringCache[it.key] = null }
    }

    override fun updateStringArray(resourceID: StringArrayResourceID, locale: String, value: Array<String>) {
        repository.updateStringArray(resourceID = resourceID, locale = locale, value = value)
    }

    override fun updatePluralStrings(
        resourceID: PluralStringResourceID,
        locale: String,
        values: Map<Quantity, String>
    ) {
        repository.updatePluralStringValues(resourceID = resourceID, locale = locale, values = values)

        computedStringCache.entries
            .filter { it.key.resourceID == resourceID && it.key.locale == locale }
            .forEach { computedStringCache[it.key] = null }
    }
}