package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType

/**
 * A [KotlinFileProducer] that creates a Kotlin file [String] with extension functions
 * on the com.chrynan.strings.accessor.Strings object for conveniently accessing [String]
 * values. For instance, if a provided [StringType] is a [StringType.Dynamic] instance,
 * then an extension function will be created with typed parameters for the [StringType.Dynamic]
 * value's arguments.
 */
class KotlinStringExtensionProducer : KotlinFileProducer {

    override fun produce(input: KotlinFileProducerInput): String {

        return """
            package ${input.packageName}
            
            
        """.trimIndent()
    }
}