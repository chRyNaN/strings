package com.chrynan.strings.accessor.coroutines

import com.chrynan.strings.accessor.StringUpdateListener
import com.chrynan.strings.core.PluralStringResourceID
import com.chrynan.strings.core.Quantity
import com.chrynan.strings.core.ResourceID
import com.chrynan.strings.core.StringArrayResourceID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class StateFlowStringUpdateListener(val listenerID: String? = null) : StringUpdateListener {

    private lateinit var stateFlow: MutableStateFlow<StringUpdatedItem>

    override fun onStringValueUpdated(resourceID: ResourceID, locale: String, value: String) {
        val item = StringUpdatedItem.StringValue(
            resourceID = resourceID,
            locale = locale,
            value = value
        )

        emitItem(item = item)
    }

    override fun onPluralStringValuesUpdated(
        resourceID: PluralStringResourceID,
        locale: String,
        values: Map<Quantity, String>
    ) {
        val item = StringUpdatedItem.PluralStringValues(
            resourceID = resourceID,
            locale = locale,
            values = values
        )

        emitItem(item = item)
    }

    override fun onStringArrayUpdated(resourceID: StringArrayResourceID, locale: String, value: Array<String>) {
        val item = StringUpdatedItem.StringArray(
            resourceID = resourceID,
            locale = locale,
            value = value.toList()
        )

        emitItem(item = item)
    }

    fun openSubscription(): Flow<StringUpdatedItem> = stateFlow

    private fun emitItem(item: StringUpdatedItem) =
        if (!::stateFlow.isInitialized) {
            stateFlow = MutableStateFlow(item)
        } else {
            stateFlow.value = item
        }
}