package com.chrynan.strings.accessor

import com.chrynan.strings.PluralStringResourceID
import com.chrynan.strings.Quantity
import com.chrynan.strings.ResourceID
import com.chrynan.strings.StringArrayResourceID

interface StringUpdateListener {

    fun onStringValueUpdated(resourceID: ResourceID, locale: String, value: String)

    fun onPluralStringValuesUpdated(resourceID: PluralStringResourceID, locale: String, values: Map<Quantity, String>)

    fun onStringArrayUpdated(resourceID: StringArrayResourceID, locale: String, value: Array<String>)
}