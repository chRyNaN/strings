package com.chrynan.strings.creator.output.android

import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.creator.core.StringTypeFileOutput
import com.chrynan.strings.creator.core.StringTypeFileProducer

/**
 * A class that produces a [StringTypeFileOutput] representation of an Android String Resource File
 * from the [produce] function.
 */
class AndroidStringResourceFileProducer : StringTypeFileProducer<AndroidStringResourceInput> {

    override fun produce(input: AndroidStringResourceInput): StringTypeFileOutput {

        return StringTypeFileOutput(
            fileName = input.fileName,
            fileText = ""
        )
    }
}