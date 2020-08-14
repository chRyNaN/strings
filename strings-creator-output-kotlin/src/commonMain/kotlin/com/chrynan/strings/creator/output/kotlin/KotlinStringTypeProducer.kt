package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.creator.core.StringTypeProducer

class KotlinStringTypeProducer(
    private val outputPackageName: String,
    private val createExtensionFunctions: Boolean = true
) : StringTypeProducer<KotlinStringTypeOutput> {

    override fun produce(types: List<StringType>): KotlinStringTypeOutput {
        val resourceIDProducer = KotlinStringResIDProducer()
        val reviserProducer = KotlinStringReviserProducer()

        val input = KotlinFileProducerInput(types = types, packageName = outputPackageName)

        val resourceIDs = resourceIDProducer(input)
        val reviser = reviserProducer(input)

        return KotlinStringTypeOutput(
            stringResourceIDs = resourceIDs,
            stringReviser = reviser
        )
    }
}