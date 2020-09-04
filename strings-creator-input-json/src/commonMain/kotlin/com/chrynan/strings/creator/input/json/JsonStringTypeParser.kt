package com.chrynan.strings.creator.input.json

import com.chrynan.strings.core.Locale
import com.chrynan.strings.core.Quantity
import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.creator.core.StringTypeName
import com.chrynan.strings.creator.core.StringTypeParser
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Converts a [JsonStringTypeInput] into a [List] of [StringType]s so that they can be used to
 * generate necessary string files. The [JsonStringTypeInput] represents a single JSON file
 * following the String Type JSON Format. To parse multiple files, create multiple
 * [JsonStringTypeInput] instances and call the [parse] function with each of them as input, and
 * aggregate their results.
 */
class JsonStringTypeParser : StringTypeParser<JsonStringTypeInput> {

    override fun parse(input: JsonStringTypeInput): List<StringType> {
        val serializer = JsonStringValueSerializer.list

        val json = Json(JsonConfiguration.Stable)

        return json.parse(serializer, input.jsonString)
            .map {
                when (it) {
                    is JsonStringValue.StringValue -> mapStringValue(it, input.fileLocale)
                    is JsonStringValue.ArrayValue -> mapArrayValue(it, input.fileLocale)
                    is JsonStringValue.PluralsValue -> mapPluralsValue(it, input.fileLocale)
                }
            }
    }

    private fun mapStringValue(value: JsonStringValue.StringValue, fileLocale: String?): StringType =
        when (StringTypeName.fromName(value.type)) {
            StringTypeName.STATIC -> StringType.Static(
                name = value.name,
                value = value.value,
                locale = value.locale ?: fileLocale ?: Locale.default
            )
            StringTypeName.DYNAMIC -> StringType.Dynamic(
                name = value.name,
                value = value.value,
                locale = value.locale ?: fileLocale ?: Locale.default
            )
            StringTypeName.HTML -> StringType.Html(
                name = value.name,
                value = value.value,
                locale = value.locale ?: fileLocale ?: Locale.default
            )
            else -> throw InvalidStringValueTypeException(
                expectedType = StringTypeName.STATIC.value,
                actualType = value.type
            )
        }

    private fun mapArrayValue(value: JsonStringValue.ArrayValue, fileLocale: String?): StringType =
        when (StringTypeName.fromName(value.type)) {
            StringTypeName.ARRAY -> StringType.Array(
                name = value.name,
                values = value.values,
                locale = value.locale ?: fileLocale ?: Locale.default
            )
            else -> throw InvalidStringValueTypeException(
                expectedType = StringTypeName.STATIC.value,
                actualType = value.type
            )
        }

    private fun mapPluralsValue(value: JsonStringValue.PluralsValue, fileLocale: String?): StringType =
        when (StringTypeName.fromName(value.type)) {
            StringTypeName.ARRAY -> StringType.Plurals(
                name = value.name,
                values = value.values.map {
                    StringType.Plurals.Item(
                        value = it.value,
                        quantity = Quantity.fromName(it.quantity) ?: Quantity.ONE
                    )
                },
                locale = value.locale ?: fileLocale ?: Locale.default
            )
            else -> throw InvalidStringValueTypeException(
                expectedType = StringTypeName.STATIC.value,
                actualType = value.type
            )
        }
}