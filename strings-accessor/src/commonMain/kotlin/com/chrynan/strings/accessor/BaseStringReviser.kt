package com.chrynan.strings.accessor

import com.chrynan.strings.core.*

abstract class BaseStringReviser : BaseStringAccessor(),
    StringReviser {

    abstract override val repository: MutableStringRepository

    override val updateListeners: MutableSet<StringUpdateListener> = mutableSetOf()

    override fun updateStaticString(resourceID: StaticStringResourceID, locale: String, value: String) {
        repository.updateStringValue(resourceID = resourceID, locale = locale, value = value)

        updateListeners.notifyStringValueUpdated(resourceID = resourceID, locale = locale, value = value)
    }

    override fun updateDynamicString(resourceID: DynamicStringResourceID, locale: String, value: String) {
        repository.updateStringValue(resourceID = resourceID, locale = locale, value = value)

        computedStringCache.entries
            .filter { it.key.resourceID == resourceID && it.key.locale == locale }
            .forEach { computedStringCache[it.key] = null }

        updateListeners.notifyStringValueUpdated(resourceID = resourceID, locale = locale, value = value)
    }

    override fun updateHtmlString(resourceID: HtmlStringResourceID, locale: String, value: String) {
        repository.updateStringValue(resourceID = resourceID, locale = locale, value = value)

        computedStringCache.entries
            .filter { it.key.resourceID == resourceID && it.key.locale == locale }
            .forEach { computedStringCache[it.key] = null }

        updateListeners.notifyStringValueUpdated(resourceID = resourceID, locale = locale, value = value)
    }

    override fun updateStringArray(resourceID: StringArrayResourceID, locale: String, value: Array<String>) {
        repository.updateStringArray(resourceID = resourceID, locale = locale, value = value)

        updateListeners.notifyStringArrayUpdated(resourceID = resourceID, locale = locale, value = value)
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

        updateListeners.notifyPluralStringValuesUpdated(resourceID = resourceID, locale = locale, values = values)
    }

    private fun Set<StringUpdateListener>.notifyStringValueUpdated(
        resourceID: ResourceID,
        locale: String,
        value: String
    ) = forEach { it.onStringValueUpdated(resourceID = resourceID, locale = locale, value = value) }

    private fun Set<StringUpdateListener>.notifyPluralStringValuesUpdated(
        resourceID: PluralStringResourceID,
        locale: String,
        values: Map<Quantity, String>
    ) = forEach { it.onPluralStringValuesUpdated(resourceID = resourceID, locale = locale, values = values) }

    private fun Set<StringUpdateListener>.notifyStringArrayUpdated(
        resourceID: StringArrayResourceID,
        locale: String,
        value: Array<String>
    ) = forEach { it.onStringArrayUpdated(resourceID = resourceID, locale = locale, value = value) }
}