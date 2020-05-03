package com.chrynan.strings.accessor

import com.chrynan.strings.PluralStringResourceID
import com.chrynan.strings.Quantity
import com.chrynan.strings.ResourceID
import com.chrynan.strings.StringArrayResourceID

sealed class StringUpdatedItem {

    data class StringValue(
        val resourceID: ResourceID,
        val locale: String,
        val value: String
    )

    data class PluralStringValues(
        val resourceID: PluralStringResourceID,
        val locale: String,
        val values: Map<Quantity, String>
    )

    data class StringArray(
        val resourceID: StringArrayResourceID,
        val locale: String,
        val value: List<String>
    )
}