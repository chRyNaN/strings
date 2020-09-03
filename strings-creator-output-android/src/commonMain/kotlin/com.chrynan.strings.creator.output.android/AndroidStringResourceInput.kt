package com.chrynan.strings.creator.output.android

import com.chrynan.strings.creator.core.StringType

/**
 * Represents the input to a [AndroidStringResourceFileProducer]. These values are used to help
 * create the Android String Resource File returned from the [AndroidStringResourceFileProducer.produce]
 * function.
 */
data class AndroidStringResourceInput(
    val types: List<StringType>,
    val fileName: String
)