package com.chrynan.strings

interface StringUpdateListener {

    fun onStringValueUpdated(resourceID: ResourceID, locale: String, value: String)

    fun onPluralStringValuesUpdated(resourceID: PluralStringResourceID, locale: String, values: Map<Quantity, String>)

    fun onStringArrayUpdated(resourceID: StringArrayResourceID, locale: String, value: Array<String>)
}