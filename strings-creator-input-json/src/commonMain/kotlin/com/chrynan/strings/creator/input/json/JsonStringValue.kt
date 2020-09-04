package com.chrynan.strings.creator.input.json

import com.chrynan.strings.creator.core.StringTypeName
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonInput
import kotlinx.serialization.json.JsonObject

@Serializable
sealed class JsonStringValue {

    abstract val type: String
    abstract val locale: String?

    @Serializable
    data class StringValue(
        @SerialName(value = "type") override val type: String,
        @SerialName(value = "locale") override val locale: String? = null,
        @SerialName(value = "name") val name: String,
        @SerialName(value = "value") val value: String
    ) : JsonStringValue()

    @Serializable
    data class ArrayValue(
        @SerialName(value = "type") override val type: String,
        @SerialName(value = "locale") override val locale: String? = null,
        @SerialName(value = "name") val name: String,
        @SerialName(value = "values") val values: List<String> = emptyList()
    ) : JsonStringValue()

    @Serializable
    data class PluralsValue(
        @SerialName(value = "type") override val type: String,
        @SerialName(value = "locale") override val locale: String? = null,
        @SerialName(value = "name") val name: String,
        @SerialName(value = "values") val values: List<PluralsItemValue> = emptyList()
    ) : JsonStringValue() {

        @Serializable
        data class PluralsItemValue(
            @SerialName(value = "quantity") val quantity: String,
            @SerialName(value = "value") val value: String
        )
    }
}

@Serializer(forClass = JsonStringValue::class)
object JsonStringValueSerializer : KSerializer<JsonStringValue> {

    override fun serialize(encoder: Encoder, value: JsonStringValue) {
        when (value) {
            is JsonStringValue.StringValue -> encoder.encode(JsonStringValue.StringValue.serializer(), value)
            is JsonStringValue.ArrayValue -> encoder.encode(JsonStringValue.ArrayValue.serializer(), value)
            is JsonStringValue.PluralsValue -> encoder.encode(JsonStringValue.PluralsValue.serializer(), value)
        }
    }

    override fun deserialize(decoder: Decoder): JsonStringValue {
        val input = decoder as? JsonInput
            ?: throw SerializationException("Expected JsonInput for $decoder when deserializing.")
        val jsonObject = input.decodeJson() as? JsonObject
            ?: throw SerializationException("Expected JsonObject when deserializing.")

        val type = jsonObject.getPrimitive("type").content

        val serializer = when (StringTypeName.fromName(type)) {
            StringTypeName.STATIC -> JsonStringValue.StringValue.serializer()
            StringTypeName.DYNAMIC -> JsonStringValue.StringValue.serializer()
            StringTypeName.HTML -> JsonStringValue.StringValue.serializer()
            StringTypeName.ARRAY -> JsonStringValue.ArrayValue.serializer()
            StringTypeName.PLURALS -> JsonStringValue.PluralsValue.serializer()
            else -> throw SerializationException("Invalid type name.")
        }

        return decoder.json.parse(serializer, jsonObject.toString())
    }
}
