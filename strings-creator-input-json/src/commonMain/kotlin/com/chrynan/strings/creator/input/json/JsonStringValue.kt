package com.chrynan.strings.creator.input.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class JsonStringValue {

    abstract val type: String

    @Serializable
    data class StringValue(
        @SerialName(value = "type") override val type: String,
        @SerialName(value = "name") val name: String,
        @SerialName(value = "value") val value: String
    ) : JsonStringValue()

    @Serializable
    data class ArrayValue(
        @SerialName(value = "type") override val type: String,
        @SerialName(value = "name") val name: String,
        @SerialName(value = "values") val values: List<String> = emptyList()
    ) : JsonStringValue()

    @Serializable
    data class PluralsValue(
        @SerialName(value = "type") override val type: String,
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

