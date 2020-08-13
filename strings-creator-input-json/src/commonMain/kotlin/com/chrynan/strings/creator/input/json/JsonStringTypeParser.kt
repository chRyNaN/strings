package com.chrynan.strings.creator.input.json

import com.chrynan.strings.core.Quantity
import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.creator.core.StringTypeName
import com.chrynan.strings.creator.core.StringTypeParser
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class JsonStringTypeParser : StringTypeParser<JsonStringTypeInput> {

    override fun parse(input: JsonStringTypeInput): List<StringType> {
        val serializer = JsonStringValue.serializer().list

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

    private fun mapStringValue(value: JsonStringValue.StringValue, locale: String): StringType =
        when (StringTypeName.fromName(value.type)) {
            StringTypeName.STATIC -> StringType.Static(name = value.name, value = value.value, locale = locale)
            StringTypeName.DYNAMIC -> StringType.Dynamic(name = value.name, value = value.value, locale = locale)
            StringTypeName.HTML -> StringType.Html(name = value.name, value = value.value, locale = locale)
            else -> throw InvalidStringValueTypeException(
                expectedType = StringTypeName.STATIC.value,
                actualType = value.type
            )
        }

    private fun mapArrayValue(value: JsonStringValue.ArrayValue, locale: String): StringType =
        when (StringTypeName.fromName(value.type)) {
            StringTypeName.ARRAY -> StringType.Array(name = value.name, values = value.values, locale = locale)
            else -> throw InvalidStringValueTypeException(
                expectedType = StringTypeName.STATIC.value,
                actualType = value.type
            )
        }

    private fun mapPluralsValue(value: JsonStringValue.PluralsValue, locale: String): StringType =
        when (StringTypeName.fromName(value.type)) {
            StringTypeName.ARRAY -> StringType.Plurals(
                name = value.name,
                values = value.values.map {
                    StringType.Plurals.Item(
                        value = it.value,
                        quantity = Quantity.fromName(it.quantity) ?: Quantity.ONE
                    )
                },
                locale = locale
            )
            else -> throw InvalidStringValueTypeException(
                expectedType = StringTypeName.STATIC.value,
                actualType = value.type
            )
        }
}