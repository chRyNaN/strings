package com.chrynan.strings.accessor

import com.chrynan.strings.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun stringChanges(resourceID: StaticStringResourceID, locale: String = Locale.default): Flow<String> =
    receiveFlowFor(resourceID = resourceID, locale = locale)
        .filterIsInstance<StringUpdatedItem.StringValue>()
        .map { it.value }
        .startWith(Strings.getStaticString(resourceID = resourceID, locale = locale))

@ExperimentalCoroutinesApi
fun htmlStringChanges(
    resourceID: HtmlStringResourceID,
    locale: String = Locale.default,
    vararg arguments: Any
): Flow<String> =
    receiveFlowFor(resourceID = resourceID, locale = locale)
        .filterIsInstance<StringUpdatedItem.StringValue>()
        .map { Strings.getHtmlString(resourceID = resourceID, locale = locale, arguments = *arguments) }
        .startWith(Strings.getHtmlString(resourceID = resourceID, locale = locale, arguments = *arguments))

@ExperimentalCoroutinesApi
fun dynamicStringChanges(
    resourceID: DynamicStringResourceID,
    locale: String = Locale.default,
    vararg arguments: Any
): Flow<String> =
    receiveFlowFor(resourceID = resourceID, locale = locale)
        .filterIsInstance<StringUpdatedItem.StringValue>()
        .map { Strings.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments) }
        .startWith(Strings.getDynamicString(resourceID = resourceID, locale = locale, arguments = *arguments))

@ExperimentalCoroutinesApi
fun stringArrayChanges(resourceID: StringArrayResourceID, locale: String = Locale.default): Flow<List<String>> =
    receiveFlowFor(resourceID = resourceID, locale = locale)
        .filterIsInstance<StringUpdatedItem.StringArray>()
        .map { it.value }
        .startWith(Strings.getStringArray(resourceID = resourceID, locale = locale).toList())

@ExperimentalCoroutinesApi
fun pluralStringChanges(
    resourceID: PluralStringResourceID,
    locale: String = Locale.default,
    quantity: Quantity,
    vararg arguments: Any
): Flow<String> =
    receiveFlowFor(resourceID = resourceID, locale = locale)
        .filterIsInstance<StringUpdatedItem.PluralStringValues>()
        .map {
            Strings.getPluralString(
                resourceID = resourceID,
                locale = locale,
                quantity = quantity,
                arguments = *arguments
            )
        }
        .startWith(
            Strings.getPluralString(
                resourceID = resourceID,
                locale = locale,
                quantity = quantity,
                arguments = *arguments
            )
        )

private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

private var listenerID: String? = null

private fun getRandomListenerID(): String = (1..32)
    .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
    .map(charPool::get)
    .joinToString("")

@ExperimentalCoroutinesApi
private fun getOrCreateBroadcastListener(): BroadcastChannelStringUpdateListener {
    var listener: BroadcastChannelStringUpdateListener? = Strings.reviser
        .updateListeners
        .firstOrNull { it is BroadcastChannelStringUpdateListener && it.listenerID == listenerID } as? BroadcastChannelStringUpdateListener

    if (listener == null) {
        listenerID = getRandomListenerID()
        listener = BroadcastChannelStringUpdateListener(listenerID = listenerID)
        Strings.reviser.updateListeners.add(listener)
    }

    return listener
}

@ExperimentalCoroutinesApi
private fun receiveFlowFor(resourceID: ResourceID, locale: String): Flow<StringUpdatedItem> =
    getOrCreateBroadcastListener()
        .openSubscription()
        .receiveAsFlow()
        .filter { it.resourceID == resourceID && it.locale == locale }

@ExperimentalCoroutinesApi
private fun <T> Flow<T>.startWith(item: T): Flow<T> = onStart { emit(item) }
