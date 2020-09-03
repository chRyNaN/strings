package com.chrynan.strings.plugin.annotation

import com.chrynan.logger.Logger
import com.chrynan.logger.logError
import com.chrynan.strings.annotation.*
import de.jensklingenberg.mpapt.model.AbstractProcessor
import de.jensklingenberg.mpapt.model.RoundEnvironment
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector

abstract class BaseStringsAnnotationProcessor : AbstractProcessor() {

    private val messageCollector by lazy {
        configuration.get(
            CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY,
            MessageCollector.NONE
        )
    }

    init {
        Logger.loggable = MessageCollectorLogger(messageCollector)
    }

    abstract fun handleAnnotatedFiles(annotatedFileElements: Set<AnnotatedFileElement>)

    override fun getSupportedAnnotationTypes(): Set<String> = setOf(
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

        val annotatedFiles = parseAnnotations(roundEnvironment)

        handleAnnotatedFiles(annotatedFiles)
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
            val error =
                IncorrectStringAnnotationUsageException(names = names)
            logError(throwable = error)
            throw error
        }
    }

    private fun parseAnnotations(roundEnvironment: RoundEnvironment): Set<AnnotatedFileElement> =
        try {
            val map = mutableMapOf<FileNameAndLocation, AnnotatedFileElement>()

            roundEnvironment.mapStaticStringsTo(map)
            roundEnvironment.mapDynamicStringsTo(map)
            roundEnvironment.mapHtmlStringsTo(map)
            roundEnvironment.mapStringPluralsTo(map)
            roundEnvironment.mapStringArraysTo(map)
            roundEnvironment.mapStringGroupsTo(map)

            map.values.toSet()
        } catch (throwable: Throwable) {
            logError(throwable = throwable, message = "Error parsing String Annotations.")
            throw throwable
        }
}