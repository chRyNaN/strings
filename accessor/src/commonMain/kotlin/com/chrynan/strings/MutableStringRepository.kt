package com.chrynan.strings

interface MutableStringRepository : StringRepository {

    fun updateStringValue(resourceID: ResourceID, locale: String, value: String)

    fun updatePluralStringValues(resourceID: PluralStringResourceID, locale: String, values: Map<Quantity, String>)

    fun updateStringArray(resourceID: StringArrayResourceID, locale: String, value: Array<String>)
}