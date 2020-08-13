package com.chrynan.strings.creator.input.json

import com.chrynan.strings.core.Locale

data class JsonStringTypeInput(
    val fileLocale: String = Locale.default,
    val jsonString: String
)