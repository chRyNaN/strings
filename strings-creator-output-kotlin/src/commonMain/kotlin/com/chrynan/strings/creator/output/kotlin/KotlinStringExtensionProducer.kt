package com.chrynan.strings.creator.output.kotlin

import com.chrynan.strings.creator.core.StringType
import com.chrynan.strings.accessor.Strings
import com.chrynan.strings.core.*
import com.chrynan.strings.creator.core.StringTypeFileOutput

/**
 * A [KotlinFileProducer] that creates a Kotlin file [String] with extension functions
 * on the [Strings] object for conveniently accessing [String] values. For instance, if a
 * provided [StringType] is a [StringType.Dynamic] instance, then an extension function will
 * be created with typed parameters for the [StringType.Dynamic] value's arguments.
 */
class KotlinStringExtensionProducer(private val stringArgumentManager: StringArgumentManager) : KotlinFileProducer {

    override fun produce(input: KotlinFileProducerInput): StringTypeFileOutput {
        val extensionFunctions = input.types.joinToString(separator = "\n\n") {
            when (it) {
                is StringType.Static -> createStaticStringExtension(it)
                is StringType.Array -> createStringArrayExtension(it)
                is StringType.Dynamic -> createDynamicStringExtension(it)
                is StringType.Html -> createHtmlStringExtension(it)
                is StringType.Plurals -> createPluralStringExtension(it)
            }
        }

        val text = """
        |    package ${input.packageName}
        |
        |    import com.chrynan.strings.core.ResourceID
        |    import com.chrynan.strings.core.StaticStringResourceID
        |    import com.chrynan.strings.core.DynamicStringResourceID
        |    import com.chrynan.strings.core.HtmlStringResourceID
        |    import com.chrynan.strings.core.PluralStringResourceID
        |    import com.chrynan.strings.core.StringArrayResourceID
        |    
        |    import com.chrynan.strings.accessor.Strings
        |    import com.chrynan.strings.core.Locale
        |    import com.chrynan.strings.core.Quantity
        |    
        |    $extensionFunctions
        """.trimMargin()

        return StringTypeFileOutput(
            fileName = "KotlinStringExtensions.kt",
            fileText = text
        )
    }

    private fun createStaticStringExtension(type: StringType.Static): String =
        """
        |    inline fun Strings.${type.name}(locale: String = Locale.default): String =
        |        Strings.getStaticString(resourceID = StringResID.${type.name}, locale = locale)
        """.trimMargin()

    private fun createStringArrayExtension(type: StringType.Array): String =
        """
        |    inline fun Strings.${type.name}(locale: String = Locale.default): Array<String> =
        |        Strings.getStringArray(resourceID = StringResID.${type.name}, locale = locale)
        """.trimMargin()

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
            prefix = "locale: String = Locale.default"
        )

        return """
        |    inline fun Strings.${type.name}($allParameters): String =
        |        Strings.getDynamicString(resourceID = StringResID.${type.name}, locale = locale, $argumentNames)
        """.trimMargin()
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
            prefix = "locale: String = Locale.default"
        )

        return """
        |    inline fun Strings.${type.name}($allParameters): String =
        |        Strings.getHtmlString(resourceID = StringResID.${type.name}, locale = locale, $argumentNames)
        """.trimMargin()
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
            prefix = "locale: String = Locale.default, quantity: Quantity,"
        )

        return """
        |    inline fun Strings.${type.name}($allParameters): String =
        |        Strings.getPluralString(resourceID = StringResID.${type.name}, locale = locale, quantity = quantity, $argumentNames)
        """.trimMargin()
    }
}
