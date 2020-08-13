package com.chrynan.strings.accessor

import com.chrynan.strings.core.PluralStringResourceID
import com.chrynan.strings.core.Quantity
import com.chrynan.strings.core.ResourceID
import com.chrynan.strings.core.StringArrayResourceID

interface StringUpdateListener {

    fun onStringValueUpdated(resourceID: ResourceID, locale: String, value: String)

    fun onPluralStringValuesUpdated(resourceID: PluralStringResourceID, locale: String, values: Map<Quantity, String>)

    fun onStringArrayUpdated(resourceID: StringArrayResourceID, locale: String, value: Array<String>)
}