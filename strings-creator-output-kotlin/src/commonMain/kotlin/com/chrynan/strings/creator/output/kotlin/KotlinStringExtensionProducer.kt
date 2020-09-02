package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.accessor.Strings
import com.chrynan.strings.core.*

/**
 * A [KotlinFileProducer] that creates a Kotlin file [String] with extension functions
 * on the [Strings] object for conveniently accessing [String] values. For instance, if a
 * provided [StringType] is a [StringType.Dynamic] instance, then an extension function will
 * be created with typed parameters for the [StringType.Dynamic] value's arguments.
 */
class KotlinStringExtensionProducer(private val stringArgumentManager: StringArgumentManager) : KotlinFileProducer {

    override fun produce(input: KotlinFileProducerInput): String {
        val extensionFunctions = input.types.joinToString(separator = "\n\n") {
            when (it) {
                is StringType.Static -> createStaticStringExtension(it)
                is StringType.Array -> createStringArrayExtension(it)
                is StringType.Dynamic -> createDynamicStringExtension(it)
                is StringType.Html -> createHtmlStringExtension(it)
                is StringType.Plurals -> createPluralStringExtension(it)
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
            
            import ${Strings::class.qualifiedName}
            import ${Locale::class.qualifiedName}
            import ${Quantity::class.qualifiedName}
            
            $extensionFunctions
        """.trimIndent()
    }

    private fun createStaticStringExtension(type: StringType.Static): String =
        """
            inline fun Strings.${type.name}(locale: String = ${Locale::class.simpleName}.default): String =
                Strings.getStaticString(resourceID = StringResID.${type.name}, locale = locale)
        """.trimIndent()

    private fun createStringArrayExtension(type: StringType.Array): String =
        """
            inline fun Strings.${type.name}(locale: String = ${Locale::class.simpleName}.default): Array<String> =
                Strings.getStringArray(resourceID = StringResID.${type.name}, locale = locale)
        """.trimIndent()

    private fun createDynamicStringExtension(type: StringType.Dynamic): String {
        val result = stringArgumentManager.parse(input = type.value)

        val arguments = result.arguments.distinctBy { it.number }

        val usedNames = mutableSetOf<String>()
        val parameters = mutableSetOf<String>()

        val argumentNames = arguments.joinToString(separator = ", ") {
            val name = if (usedNames.contains(it.name)) "${it.name}${it.number}" else it.name

            usedNames.add(name)

            parameters.add("$name: ${it.type}")

            name
        }

        val allParameters = parameters.joinToString(
            separator = ", ",
            prefix = "locale: String = ${Locale::class.simpleName}.default"
        )

        return """
            inline fun Strings.${type.name}($allParameters): String =
                Strings.getDynamicString(resourceID = StringResID.${type.name}, locale = locale, $argumentNames)
        """.trimIndent()
    }

    private fun createHtmlStringExtension(type: StringType.Html): String {
        val result = stringArgumentManager.parse(input = type.value)

        val arguments = result.arguments.distinctBy { it.number }

        val usedNames = mutableSetOf<String>()
        val parameters = mutableSetOf<String>()

        val argumentNames = arguments.joinToString(separator = ", ") {
            val name = if (usedNames.contains(it.name)) "${it.name}${it.number}" else it.name

            usedNames.add(name)

            parameters.add("$name: ${it.type}")

            name
        }

        val allParameters = parameters.joinToString(
            separator = ", ",
            prefix = "locale: String = ${Locale::class.simpleName}.default"
        )

        return """
            inline fun Strings.${type.name}($allParameters): String =
                Strings.getHtmlString(resourceID = StringResID.${type.name}, locale = locale, $argumentNames)
        """.trimIndent()
    }

    private fun createPluralStringExtension(type: StringType.Plurals): String {
        val result = stringArgumentManager.parse(input = type.values.first().value)

        val arguments = result.arguments.distinctBy { it.number }

        val usedNames = mutableSetOf<String>()
        val parameters = mutableSetOf<String>()

        val argumentNames = arguments.joinToString(separator = ", ") {
            val name = if (usedNames.contains(it.name)) "${it.name}${it.number}" else it.name

            usedNames.add(name)

            parameters.add("$name: ${it.type}")

            name
        }

        val allParameters = parameters.joinToString(
            separator = ", ",
            prefix = "locale: String = ${Locale::class.simpleName}.default, quantity: ${Quantity::class.simpleName},"
        )

        return """
            inline fun Strings.${type.name}($allParameters): String =
                Strings.getPluralString(resourceID = StringResID.${type.name}, locale = locale, quantity = quantity, $argumentNames)
        """.trimIndent()
    }
}