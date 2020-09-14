package com.chrynan.strings.example.json

import com.chrynan.strings.core.RegexStringArgumentManager
import com.chrynan.strings.core.StringArgumentManager
import com.chrynan.strings.accessor.BaseStringReviser
import com.chrynan.strings.accessor.MapStringRepository
import com.chrynan.strings.accessor.MapComputedStringCache

class KotlinStrings : BaseStringReviser() {

    private val strings: Map<MapStringRepository.Key, String> by lazy {
        mapOf<MapStringRepository.Key, String>(
            Key(resourceID = StringResID.helloWorld, locale = en, quantity = null) to Hello World
        )
    }

    private val arrays: Map<MapStringRepository.Key, Array<String>> by lazy {
        mapOf<MapStringRepository.Key, Array<String>>(
            
        )
    }

    override val argumentManager by lazy { RegexStringArgumentManager() }

    override val repository by lazy {
        MapStringRepository(
            stringMap = strings,
            stringArrayMap = arrays
        )
    }

    override val computedStringCache by lazy { MapComputedStringCache() }
}