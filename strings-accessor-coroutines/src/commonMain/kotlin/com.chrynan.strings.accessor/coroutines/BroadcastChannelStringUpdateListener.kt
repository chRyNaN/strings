package com.chrynan.strings.accessor.coroutines

import com.chrynan.strings.accessor.StringUpdateListener
import com.chrynan.strings.core.PluralStringResourceID
import com.chrynan.strings.core.Quantity
import com.chrynan.strings.core.ResourceID
import com.chrynan.strings.core.StringArrayResourceID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
class BroadcastChannelStringUpdateListener(val listenerID: String? = null) :
    StringUpdateListener {

    private val broadcastChannel = ConflatedBroadcastChannel<StringUpdatedItem>()

    override fun onStringValueUpdated(resourceID: ResourceID, locale: String, value: String) {
        broadcastChannel.offer(
            StringUpdatedItem.StringValue(
                resourceID = resourceID,
                locale = locale,
                value = value
            )
        )
    }

    override fun onPluralStringValuesUpdated(
        resourceID: PluralStringResourceID,
        locale: String,
        values: Map<Quantity, String>
    ) {
        broadcastChannel.offer(
            StringUpdatedItem.PluralStringValues(
                resourceID = resourceID,
                locale = locale,
                values = values
            )
        )
    }

    override fun onStringArrayUpdated(resourceID: StringArrayResourceID, locale: String, value: Array<String>) {
        broadcastChannel.offer(
            StringUpdatedItem.StringArray(
                resourceID = resourceID,
                locale = locale,
                value = value.toList()
            )
        )
    }

    fun openSubscription(): ReceiveChannel<StringUpdatedItem> = broadcastChannel.openSubscription()
}