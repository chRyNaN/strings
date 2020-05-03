package com.chrynan.strings.accessor

import com.chrynan.strings.PluralStringResourceID
import com.chrynan.strings.Quantity
import com.chrynan.strings.ResourceID
import com.chrynan.strings.StringArrayResourceID

class FlowStringUpdateListener : StringUpdateListener {

    private var delegateListener: StringUpdateListener? = null

    override fun onStringValueUpdated(resourceID: ResourceID, locale: String, value: String) {
        delegateListener?.onStringValueUpdated(resourceID = resourceID, locale = locale, value = value)
    }

    override fun onPluralStringValuesUpdated(
        resourceID: PluralStringResourceID,
        locale: String,
        values: Map<Quantity, String>
    ) {
        delegateListener?.onPluralStringValuesUpdated(resourceID = resourceID, locale = locale, values = values)
    }

    override fun onStringArrayUpdated(resourceID: StringArrayResourceID, locale: String, value: Array<String>) {
        delegateListener?.onStringArrayUpdated(resourceID = resourceID, locale = locale, value = value)
    }
}