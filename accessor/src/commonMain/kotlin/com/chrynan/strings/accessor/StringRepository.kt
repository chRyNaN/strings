package com.chrynan.strings.accessor

import com.chrynan.strings.PluralStringResourceID
import com.chrynan.strings.Quantity
import com.chrynan.strings.ResourceID
import com.chrynan.strings.StringArrayResourceID

interface StringRepository {

    fun getStringValue(resourceID: ResourceID, locale: String): String

    fun getPluralStringValue(resourceID: PluralStringResourceID, locale: String, quantity: Quantity): String

    fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String>
}