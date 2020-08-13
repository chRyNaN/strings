package com.chrynan.strings.accessor

import com.chrynan.strings.core.PluralStringResourceID
import com.chrynan.strings.core.Quantity
import com.chrynan.strings.core.ResourceID
import com.chrynan.strings.core.StringArrayResourceID

sealed class StringUpdatedItem {

    abstract val resourceID: ResourceID
    abstract val locale: String

    data class StringValue(
        override val resourceID: ResourceID,
        override val locale: String,
        val value: String
    ) : StringUpdatedItem()

    data class PluralStringValues(
        override val resourceID: PluralStringResourceID,
        override val locale: String,
        val values: Map<Quantity, String>
    ) : StringUpdatedItem()

    data class StringArray(
        override val resourceID: StringArrayResourceID,
        override val locale: String,
        val value: List<String>
    ) : StringUpdatedItem()
}