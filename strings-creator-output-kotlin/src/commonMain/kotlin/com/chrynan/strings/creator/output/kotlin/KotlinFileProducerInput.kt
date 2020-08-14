package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType

data class KotlinFileProducerInput(
    val types: List<StringType>,
    val packageName: String
)