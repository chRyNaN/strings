package com.chrynan.strings

import com.chrynan.logger.Logger
import com.chrynan.logger.logError
import de.jensklingenberg.mpapt.model.AbstractProcessor
import de.jensklingenberg.mpapt.model.RoundEnvironment

class StringsAnnotationProcessor : AbstractProcessor() {

    init {
        Logger.loggable = AbstractProcessorLogger(this)
    }

    override fun getSupportedAnnotationTypes(): Set<kotlin.String> = setOf(
        StaticString::class.java.name,
        DynamicString::class.java.name,
        HtmlString::class.java.name,
        StringPlurals::class.java.name,
        StringArray::class.java.name,
        StringGroup::class.java.name,
        StringGroupItem::class.java.name,
        StringArrayItem::class.java.name,
        StringPluralItem::class.java.name
    )

    override fun process(roundEnvironment: RoundEnvironment) {
        handleIncorrectUsage(roundEnvironment)


    }

    private fun handleIncorrectUsage(roundEnvironment: RoundEnvironment) {
        // These Annotations should only be used within other Annotations
        val names = listOf(
            StringGroupItem::class.java.name,
            StringArrayItem::class.java.name,
            StringPluralItem::class.java.name
        )
        val items = roundEnvironment.getElementsAnnotatedWith(names)

        if (items.isNotEmpty()) {
            val error = IncorrectStringAnnotationUsageException(names = names)
            logError(throwable = error)
            throw error
        }
    }
}