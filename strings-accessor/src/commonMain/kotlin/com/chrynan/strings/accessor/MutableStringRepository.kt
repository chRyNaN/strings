package com.chrynan.strings.accessor

import com.chrynan.strings.core.PluralStringResourceID
import com.chrynan.strings.core.Quantity
import com.chrynan.strings.core.ResourceID
import com.chrynan.strings.core.StringArrayResourceID

interface MutableStringRepository : StringRepository {

    fun updateStringValue(resourceID: ResourceID, locale: String, value: String)

    fun updatePluralStringValues(resourceID: PluralStringResourceID, locale: String, values: Map<Quantity, String>)

    fun updateStringArray(resourceID: StringArrayResourceID, locale: String, value: Array<String>)
}