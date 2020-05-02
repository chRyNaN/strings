package com.chrynan.strings

fun StringAccessor.getStringOrNull(resourceID: StringResourceID, locale: String = Locale.default): String? =
    try {
        getStaticString(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getDynamicStringOrNull(
    resourceID: DynamicStringResourceID,
    locale: String = Locale.default,
    vararg arguments: Any
): String? =
    try {
        getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getHtmlStringOrNull(
    resourceID: HtmlStringResourceID,
    locale: String = Locale.default
): String? =
    try {
        getHtmlString(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getStringArrayOrNull(
    resourceID: StringArrayResourceID,
    locale: String = Locale.default
): Array<String>? =
    try {
        getStringArray(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getStringArrayOrEmpty(
    resourceID: StringArrayResourceID,
    locale: String = Locale.default
): Array<String> =
    try {
        getStringArray(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        emptyArray()
    }

fun StringAccessor.getPluralStringOrNull(
    resourceID: PluralStringResourceID,
    locale: String = Locale.default,
    quantity: Quantity,
    vararg arguments: Any
): String? =
    try {
        getPluralString(resourceID = resourceID, quantity = quantity, locale = locale, arguments = *arguments)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }