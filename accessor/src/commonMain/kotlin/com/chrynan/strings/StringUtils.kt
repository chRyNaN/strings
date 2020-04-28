package com.chrynan.strings

fun string(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String> =
    lazy { Strings.getString(resourceID = resourceID, locale = locale) }

fun stringArray(resourceID: StringArrayResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>> =
    lazy { Strings.getStringArray(resourceID = resourceID, locale = locale) }

fun htmlString(resourceID: HtmlStringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String> =
    lazy { Strings.getHtmlString(resourceID = resourceID, locale = locale) }

fun pluralString(
    resourceID: PluralStringResourceID,
    quantity: Quantity,
    locale: Locale = Locale.default
): Lazy<kotlin.String> =
    lazy { Strings.getPluralString(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringFormatter(
    resourceID: PluralStringResourceID,
    locale: Locale = Locale.default
): (quantity: Quantity, arguments: Array<Any>) -> kotlin.String =
    { quantity, arguments ->
        Strings.getPluralString(
            resourceID = resourceID,
            locale = locale,
            quantity = quantity,
            arguments = *arguments
        )
    }

fun dynamicStringFormatter(
    resourceID: DynamicStringResourceID,
    locale: Locale = Locale.default
): (arguments: Array<Any>) -> kotlin.String =
    { arguments ->
        Strings.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)
    }

fun stringOrNull(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String?> =
    lazy { Strings.getStringOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrNull(resourceID: StringArrayResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>?> =
    lazy { Strings.getStringArrayOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrEmpty(resourceID: StringArrayResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>> =
    lazy { Strings.getStringArrayOrEmpty(resourceID = resourceID, locale = locale) }

fun htmlStringOrNull(resourceID: HtmlStringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String?> =
    lazy { Strings.getHtmlStringOrNull(resourceID = resourceID, locale = locale) }

fun pluralStringOrNull(
    resourceID: PluralStringResourceID,
    quantity: Quantity,
    locale: Locale = Locale.default
): Lazy<kotlin.String?> =
    lazy { Strings.getPluralStringOrNull(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringOrNullFormatter(
    resourceID: PluralStringResourceID,
    locale: Locale = Locale.default
): (quantity: Quantity, arguments: Array<Any>) -> kotlin.String? =
    { quantity, arguments ->
        Strings.getPluralStringOrNull(
            resourceID = resourceID,
            locale = locale,
            quantity = quantity,
            arguments = *arguments
        )
    }

fun dynamicStringOrNullFormatter(
    resourceID: DynamicStringResourceID,
    locale: Locale = Locale.default
): (arguments: Array<Any>) -> kotlin.String? =
    { arguments ->
        Strings.getDynamicStringOrNull(resourceID = resourceID, locale = locale, arguments = *arguments)
    }