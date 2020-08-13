package com.chrynan.strings.accessor

import com.chrynan.strings.core.*

fun string(resourceID: StaticStringResourceID, locale: String = Locale.default): Lazy<String> =
    lazy { Strings.getStaticString(resourceID = resourceID, locale = locale) }

fun stringArray(resourceID: StringArrayResourceID, locale: String = Locale.default): Lazy<Array<String>> =
    lazy { Strings.getStringArray(resourceID = resourceID, locale = locale) }

fun htmlString(resourceID: HtmlStringResourceID, locale: String = Locale.default): Lazy<String> =
    lazy { Strings.getHtmlString(resourceID = resourceID, locale = locale) }

fun pluralString(
    resourceID: PluralStringResourceID,
    quantity: Quantity,
    locale: String = Locale.default
): Lazy<String> =
    lazy { Strings.getPluralString(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringFormatter(
    resourceID: PluralStringResourceID,
    locale: String = Locale.default
): (quantity: Quantity, arguments: Array<Any>) -> String =
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
    locale: String = Locale.default
): (arguments: Array<Any>) -> String =
    { arguments ->
        Strings.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments)
    }

fun htmlStringFormatter(
    resourceID: HtmlStringResourceID,
    locale: String = Locale.default
): (arguments: Array<Any>) -> String =
    { arguments ->
        Strings.getHtmlString(resourceID = resourceID, locale = locale, arguments = *arguments)
    }

fun stringOrNull(resourceID: StaticStringResourceID, locale: String = Locale.default): Lazy<String?> =
    lazy { Strings.getStringOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrNull(resourceID: StringArrayResourceID, locale: String = Locale.default): Lazy<Array<String>?> =
    lazy { Strings.getStringArrayOrNull(resourceID = resourceID, locale = locale) }

fun stringArrayOrEmpty(resourceID: StringArrayResourceID, locale: String = Locale.default): Lazy<Array<String>> =
    lazy { Strings.getStringArrayOrEmpty(resourceID = resourceID, locale = locale) }

fun htmlStringOrNull(resourceID: HtmlStringResourceID, locale: String = Locale.default): Lazy<String?> =
    lazy { Strings.getHtmlStringOrNull(resourceID = resourceID, locale = locale) }

fun pluralStringOrNull(
    resourceID: PluralStringResourceID,
    quantity: Quantity,
    locale: String = Locale.default
): Lazy<String?> =
    lazy { Strings.getPluralStringOrNull(resourceID = resourceID, quantity = quantity, locale = locale) }

fun pluralStringOrNullFormatter(
    resourceID: PluralStringResourceID,
    locale: String = Locale.default
): (quantity: Quantity, arguments: Array<Any>) -> String? =
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
    locale: String = Locale.default
): (arguments: Array<Any>) -> String? =
    { arguments ->
        Strings.getDynamicStringOrNull(resourceID = resourceID, locale = locale, arguments = *arguments)
    }

fun htmlStringOrNullFormatter(
    resourceID: HtmlStringResourceID,
    locale: String = Locale.default
): (arguments: Array<Any>) -> String =
    { arguments ->
        Strings.getHtmlString(resourceID = resourceID, locale = locale, arguments = *arguments)
    }