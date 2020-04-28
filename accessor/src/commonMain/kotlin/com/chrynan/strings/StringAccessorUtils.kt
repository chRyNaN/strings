package com.chrynan.strings

fun StringAccessor.getStringOrNull(resourceID: StringResourceID, locale: Locale = Locale.default): kotlin.String? =
    try {
        getString(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getDynamicStringOrNull(
    resourceID: DynamicStringResourceID,
    locale: Locale = Locale.default,
    vararg arguments: Any
): kotlin.String? =
    try {
        getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getHtmlStringOrNull(
    resourceID: HtmlStringResourceID,
    locale: Locale = Locale.default
): kotlin.String? =
    try {
        getHtmlString(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getStringArrayOrNull(
    resourceID: StringArrayResourceID,
    locale: Locale = Locale.default
): Array<kotlin.String>? =
    try {
        getStringArray(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }

fun StringAccessor.getStringArrayOrEmpty(
    resourceID: StringArrayResourceID,
    locale: Locale = Locale.default
): Array<kotlin.String> =
    try {
        getStringArray(resourceID = resourceID, locale = locale)
    } catch (exception: StringResourceIDNotFoundException) {
        emptyArray()
    }

fun StringAccessor.getPluralStringOrNull(
    resourceID: PluralStringResourceID,
    locale: Locale = Locale.default,
    quantity: Quantity,
    vararg arguments: Any
): kotlin.String? =
    try {
        getPluralString(resourceID = resourceID, quantity = quantity, locale = locale, arguments = *arguments)
    } catch (exception: StringResourceIDNotFoundException) {
        null
    }