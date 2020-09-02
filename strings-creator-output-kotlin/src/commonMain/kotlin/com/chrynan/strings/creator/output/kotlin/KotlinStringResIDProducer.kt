package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.core.*
import com.chrynan.strings.creator.core.StringType

class KotlinStringResIDProducer : KotlinFileProducer {

    override fun produce(input: KotlinFileProducerInput): String {
        val ids = buildString {
            input.types.forEach {
                append(createStringResourceIDDefinition(it))
                append("\n")
            }
        }

        return """
            package ${input.packageName}
            
            import ${ResourceID::class.qualifiedName}
            import ${StaticStringResourceID::class.qualifiedName}
            import ${DynamicStringResourceID::class.qualifiedName}
            import ${HtmlStringResourceID::class.qualifiedName}
            import ${PluralStringResourceID::class.qualifiedName}
            import ${StringArrayResourceID::class.qualifiedName}

            object StringResID {
                
                $ids
            }
        """.trimIndent()
    }

    private fun createStringResourceIDDefinition(type: StringType): String {
        val instantiation = when (type) {
            is StringType.Static -> "StaticStringResourceID(name = ${type.name})"
            is StringType.Dynamic -> "DynamicStringResourceID(name = ${type.name})"
            is StringType.Html -> "HtmlStringResourceID(name = ${type.name})"
            is StringType.Plurals -> "PluralStringResourceID(name = ${type.name})"
            is StringType.Array -> "StringArrayResourceID(name = ${type.name})"
        }

        return "val ${type.name} = $instantiation"
    }
}
