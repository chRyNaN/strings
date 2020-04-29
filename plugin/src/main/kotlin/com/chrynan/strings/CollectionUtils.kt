package com.chrynan.strings

fun <T, K, V> Iterable<T>.associateAllBy(keySelector: (T) -> K, valueSelector: (T) -> V): Map<K, Set<V>> {
    val map = mutableMapOf<K, MutableSet<V>>()

    for (element in this) {
        val key = keySelector(element)
        val set = map[key] ?: mutableSetOf()

        set.add(valueSelector(element))

        map[key] = set
    }

    return map
}

fun <T, K, R> Map<K, T>.assignEachTo(map: MutableMap<K, R>, transformer: (K, T, R?) -> R): Map<K, T> {
    forEach {
        val element = map[it.key]

        val value = transformer(it.key, it.value, element)

        map[it.key] = value
    }

    return this
}