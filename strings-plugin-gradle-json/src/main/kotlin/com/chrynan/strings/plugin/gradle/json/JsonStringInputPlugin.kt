package com.chrynan.strings.plugin.gradle.json

import com.chrynan.strings.core.RegexStringArgumentManager
import com.chrynan.strings.creator.input.json.JsonStringTypeInput
import com.chrynan.strings.creator.input.json.JsonStringTypeParser
import com.chrynan.strings.creator.output.kotlin.KotlinFileProducerInput
import com.chrynan.strings.creator.output.kotlin.KotlinStringExtensionProducer
import com.chrynan.strings.creator.output.kotlin.KotlinStringResIDProducer
import com.chrynan.strings.creator.output.kotlin.KotlinStringReviserProducer
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class JsonStringInputPlugin : Plugin<Project> {

    companion object {

        private const val EXTENSION_NAME = "jsonStrings"
        private const val TASK_NAME_GENERATE = "generateStringsFromJsonInput"
        private const val GROUP_NAME = "strings"
    }

    private val jsonStringTypeParser = JsonStringTypeParser()
    private val kotlinStringExtensionProducer = KotlinStringExtensionProducer(RegexStringArgumentManager())
    private val kotlinStringResIDProducer = KotlinStringResIDProducer()
    private val kotlinStringReviserProducer = KotlinStringReviserProducer()

    override fun apply(target: Project) {
        val extension = target.extensions.create(EXTENSION_NAME, JsonStringInputExtension::class.java)

        target.tasks.register(TASK_NAME_GENERATE) { task ->
            task.group = GROUP_NAME

            task.doFirst {
                val stringTypes = extension.inputPaths.map { File(it) }
                    .map { file ->
                        val fileLocale =
                            extension.fileNameLocaleSeparator?.let { file.name.substringAfter(it) }

                        JsonStringTypeInput(
                            fileName = file.name,
                            fileLocale = if (fileLocale.isNullOrBlank()) null else fileLocale,
                            jsonString = file.readText()
                        )
                    }.flatMap { jsonStringTypeParser.parse(it) }

                val outputPath = extension.outputPath
                val outputPackageName = extension.outputPackageName

                if (outputPath != null && outputPackageName != null) {
                    val fileProducerInput = KotlinFileProducerInput(
                        types = stringTypes,
                        packageName = outputPackageName
                    )

                    val kotlinExtension = target.extensions.getByName("kotlin") as KotlinMultiplatformExtension
                    val sourceSet = kotlinExtension.sourceSets.getByName(KotlinSourceSet.COMMON_MAIN_SOURCE_SET_NAME)

                    sourceSet.kotlin

                    val resIDOutput = kotlinStringResIDProducer.produce(fileProducerInput)
                    val reviserOutput = kotlinStringReviserProducer.produce(fileProducerInput)

                    val outputDirectory = File(outputPath)

                    if (!outputDirectory.exists()) outputDirectory.mkdirs()

                    val resIDFile = File("$outputPath/${resIDOutput.fileName}")
                    val reviserFile = File("$outputPath/${reviserOutput.fileName}")

                    if (!resIDFile.exists()) resIDFile.createNewFile()
                    if (!reviserFile.exists()) reviserFile.createNewFile()

                    resIDFile.writeText(resIDOutput.fileText)
                    reviserFile.writeText(reviserOutput.fileText)

                    if (extension.generateExtensionFunctions) {
                        val extensionOutput = kotlinStringExtensionProducer.produce(fileProducerInput)

                        val extensionFile = File("$outputPath/${extensionOutput.fileName}")

                        if (!extensionFile.exists()) extensionFile.mkdirs()

                        extensionFile.writeText(extensionOutput.fileText)
                    }
                }
            }
        }
    }
}