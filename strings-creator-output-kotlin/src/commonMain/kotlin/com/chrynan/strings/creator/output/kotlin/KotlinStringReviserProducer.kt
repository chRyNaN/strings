package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.creator.core.StringTypeFileOutput

class KotlinStringReviserProducer : KotlinFileProducer {

    override fun produce(input: KotlinFileProducerInput): StringTypeFileOutput {
        val mapEntries = StringBuilder()
        val arrayMapEntries = StringBuilder()

        input.types.forEachIndexed { index, stringType ->
            if (stringType is StringType.Array) {
                if (index != 0) {
                    arrayMapEntries.append(",\n")
                }

                arrayMapEntries.append(createMapEntry(stringType))
            } else {
                if (index != 0) {
                    mapEntries.append(",\n")
                }

                mapEntries.append(createMapEntry(stringType))
            }
        }

        val text = """
        |    package ${input.packageName}
        |
        |    import com.chrynan.strings.core.RegexStringArgumentManager
        |    import com.chrynan.strings.core.StringArgumentManager
        |    import com.chrynan.strings.accessor.BaseStringReviser
        |    import com.chrynan.strings.accessor.MapStringRepository
        |    import com.chrynan.strings.accessor.MapComputedStringCache
        |
        |    class KotlinStrings : BaseStringReviser() {
        |
        |        private val strings: Map<MapStringRepository.Key, String> by lazy {
        |            mapOf<MapStringRepository.Key, String>(
        |                $mapEntries
        |            )
        |        }
        |
        |        private val arrays: Map<MapStringRepository.Key, Array<String>> by lazy {
        |            mapOf<MapStringRepository.Key, Array<String>>(
        |                $arrayMapEntries
        |            )
        |        }
        |
        |        override val argumentManager by lazy { RegexStringArgumentManager() }
        |
        |        override val repository by lazy {
        |            MapStringRepository(
        |                stringMap = strings,
        |                stringArrayMap = arrays
        |            )
        |        }
        |
        |        override val computedStringCache by lazy { MapComputedStringCache() }
        |    }
        """.trimMargin()

        return StringTypeFileOutput(
            fileName = "KotlinStrings.kt",
            fileText = text
        )
    }

    private fun createMapEntry(type: StringType): String =
        when (type) {
            is StringType.Static -> "MapStringRepository.Key(resourceID = StringResID.${type.name}, locale = \"${type.locale}\", quantity = null) to \"${type.value}\""
            is StringType.Dynamic -> "MapStringRepository.Key(resourceID = StringResID.${type.name}, locale = \"${type.locale}\", quantity = null) to \"${type.value}\""
            is StringType.Html -> "MapStringRepository.Key(resourceID = StringResID.${type.name}, locale = \"${type.locale}\", quantity = null) to \"${type.value}\""
            is StringType.Array -> createArrayMapEntry(type)
            is StringType.Plurals -> createPluralsMapEntry(type)
        }

    private fun createArrayMapEntry(type: StringType.Array): String {
        val arrayItems = type.values.joinToString(separator = ", ", prefix = "arrayOf(", postfix = ")") { "\"$it\"" }

        return "MapStringRepository.Key(resourceID = StringResID.${type.name}, locale = \"${type.locale}\", quantity = null) to $arrayItems"
    }

    private fun createPluralsMapEntry(type: StringType.Plurals): String = buildString {
        type.values.forEachIndexed { index, item ->
            if (index != 0) {
                append(",\n")
            }

            append("MapStringRepository.Key(resourceID = StringResID.${type.name}, locale = \"${type.locale}\", quantity = ${item.quantity}) to ${item.value}")
        }
    }
}
