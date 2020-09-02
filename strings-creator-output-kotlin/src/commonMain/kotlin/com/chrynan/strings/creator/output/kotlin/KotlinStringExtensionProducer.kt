package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.accessor.Strings
import com.chrynan.strings.annotation.StringPlurals
import com.chrynan.strings.core.*

/**
 * A [KotlinFileProducer] that creates a Kotlin file [String] with extension functions
 * on the [Strings] object for conveniently accessing [String] values. For instance, if a
 * provided [StringType] is a [StringType.Dynamic] instance, then an extension function will
 * be created with typed parameters for the [StringType.Dynamic] value's arguments.
 */
class KotlinStringExtensionProducer : KotlinFileProducer {

    override fun produce(input: KotlinFileProducerInput): String {

        return """
            package ${input.packageName}
            
            import ${ResourceID::class.qualifiedName}
            import ${StaticStringResourceID::class.qualifiedName}
            import ${DynamicStringResourceID::class.qualifiedName}
            import ${HtmlStringResourceID::class.qualifiedName}
            import ${PluralStringResourceID::class.qualifiedName}
            import ${StringArrayResourceID::class.qualifiedName}
            
            import com.chrynan.strings.accessor.Strings
            
            
        """.trimIndent()
    }
}