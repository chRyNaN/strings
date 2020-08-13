package com.chrynan.strings.accessor

import com.chrynan.strings.core.PluralStringResourceID
import com.chrynan.strings.core.Quantity
import com.chrynan.strings.core.ResourceID
import com.chrynan.strings.core.StringArrayResourceID

interface StringRepository {

    fun getStringValue(resourceID: ResourceID, locale: String): String

    fun getPluralStringValue(resourceID: PluralStringResourceID, locale: String, quantity: Quantity): String

    fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String>
}