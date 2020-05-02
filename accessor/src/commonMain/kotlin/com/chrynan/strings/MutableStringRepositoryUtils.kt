package com.chrynan.strings

fun MutableStringRepository.updateStringArray(
    resourceID: StringArrayResourceID,
    locale: String,
    value: Collection<String>
) = updateStringArray(resourceID = resourceID, locale = locale, value = value.toTypedArray())