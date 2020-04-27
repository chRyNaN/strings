package com.chrynan.resources

fun string(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String> =
    lazy { StringProvider.getString(resourceID = resourceID, locale = locale) }

fun stringArray(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>> =
    lazy { StringProvider.getStringArray(resourceID = resourceID, locale = locale) }

fun htmlString(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String> =
    lazy { StringProvider.getHtmlString(resourceID = resourceID, locale = locale) }

fun pluralString(
    resourceID: StringResourceID,
    quantity: Quantity,
    locale: Locale = Locale.default
): Lazy<kotlin.String> =
    lazy { StringProvider.getPluralString(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringFormatter(
    resourceID: StringResourceID,
    locale: Locale = Locale.default
): (quantity: Quantity, arguments: Array<Any>) -> kotlin.String =
    { quantity, arguments ->
        StringProvider.getPluralString(
            resourceID = resourceID,
            locale = locale,
            quantity = quantity,
            arguments = *arguments
        )
    }

fun dynamicStringFormatter(
    resourceID: StringResourceID,
    locale: Locale = Locale.default
): (arguments: Array<Any>) -> kotlin.String =
    { arguments ->
        StringProvider.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)
    }

fun stringOrNull(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String?> =
    lazy { StringProvider.getStringOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrNull(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>?> =
    lazy { StringProvider.getStringArrayOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrEmpty(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>> =
    lazy { StringProvider.getStringArrayOrEmpty(resourceID = resourceID, locale = locale) }

fun htmlStringOrNull(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String?> =
    lazy { StringProvider.getHtmlStringOrNull(resourceID = resourceID, locale = locale) }

fun pluralStringOrNull(
    resourceID: StringResourceID,
    quantity: Quantity,
    locale: Locale = Locale.default
): Lazy<kotlin.String?> =
    lazy { StringProvider.getPluralStringOrNull(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringOrNullFormatter(
    resourceID: StringResourceID,
    locale: Locale = Locale.default
): (quantity: Quantity, arguments: Array<Any>) -> kotlin.String? =
    { quantity, arguments ->
        StringProvider.getPluralStringOrNull(
            resourceID = resourceID,
            locale = locale,
            quantity = quantity,
            arguments = *arguments
        )
    }

fun dynamicStringOrNullFormatter(
    resourceID: StringResourceID,
    locale: Locale = Locale.default
): (arguments: Array<Any>) -> kotlin.String? =
    { arguments ->
        StringProvider.getDynamicStringOrNull(resourceID = resourceID, locale = locale, arguments = *arguments)
    }