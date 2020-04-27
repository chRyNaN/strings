package com.chrynan.resources

fun string(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String> =
    lazy { StringProvider.getString(resourceID = resourceID, locale = locale) }

fun stringArray(resourceID: StringArrayResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>> =
    lazy { StringProvider.getStringArray(resourceID = resourceID, locale = locale) }

fun htmlString(resourceID: HtmlStringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String> =
    lazy { StringProvider.getHtmlString(resourceID = resourceID, locale = locale) }

fun pluralString(
    resourceID: PluralStringResourceID,
    quantity: Quantity,
    locale: Locale = Locale.default
): Lazy<kotlin.String> =
    lazy { StringProvider.getPluralString(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringFormatter(
    resourceID: PluralStringResourceID,
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
    resourceID: DynamicStringResourceID,
    locale: Locale = Locale.default
): (arguments: Array<Any>) -> kotlin.String =
    { arguments ->
        StringProvider.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)
    }

fun stringOrNull(resourceID: StringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String?> =
    lazy { StringProvider.getStringOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrNull(resourceID: StringArrayResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>?> =
    lazy { StringProvider.getStringArrayOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrEmpty(resourceID: StringArrayResourceID, locale: Locale = Locale.default): Lazy<Array<kotlin.String>> =
    lazy { StringProvider.getStringArrayOrEmpty(resourceID = resourceID, locale = locale) }

fun htmlStringOrNull(resourceID: HtmlStringResourceID, locale: Locale = Locale.default): Lazy<kotlin.String?> =
    lazy { StringProvider.getHtmlStringOrNull(resourceID = resourceID, locale = locale) }

fun pluralStringOrNull(
    resourceID: PluralStringResourceID,
    quantity: Quantity,
    locale: Locale = Locale.default
): Lazy<kotlin.String?> =
    lazy { StringProvider.getPluralStringOrNull(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringOrNullFormatter(
    resourceID: PluralStringResourceID,
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
    resourceID: DynamicStringResourceID,
    locale: Locale = Locale.default
): (arguments: Array<Any>) -> kotlin.String? =
    { arguments ->
        StringProvider.getDynamicStringOrNull(resourceID = resourceID, locale = locale, arguments = *arguments)
    }