package com.chrynan.strings.creator.input.json

import com.chrynan.strings.core.Locale

/**
 * Represents a JSON String Type File as input for the [JsonStringTypeParser].
 *
 * @property [fileName] The name of the file being parsed.
 * @property [fileLocale] The optional locale provided in the file name. Each element can override
 * this value with their own [JsonStringValue.locale] property. But if one isn't present, then this
 * value is used, and if this value isn't present, then the [Locale.default] value is used.
 * @property [jsonString] The [String] representation of the JSON from the file.
 */
data class JsonStringTypeInput(
    val fileName: String,
    val fileLocale: String? = null,
    val jsonString: String
)