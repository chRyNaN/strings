package com.chrynan.strings.creator.output.kotlin

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
            
            import com.chrynan.strings.core.ResourceID
            import com.chrynan.strings.core.StaticStringResourceID
            import com.chrynan.strings.core.DynamicStringResourceID
            import com.chrynan.strings.core.HtmlStringResourceID
            import com.chrynan.strings.core.PluralStringResourceID
            import com.chrynan.strings.core.StringArrayResourceID

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
