package com.chrynan.resources

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class CreateResourcesTask : DefaultTask() {

    lateinit var androidResourceLocation: String
    lateinit var iosResourceLocation: String
    lateinit var commonGeneratedSourceLocation: String

    var strings: Set<ResourceFile.StringResourcesFile> = emptySet()
    var booleans: Set<ResourceFile.BooleanResourcesFile> = emptySet()
    var integers: Set<ResourceFile.IntegerResourcesFile> = emptySet()

    private val androidResourcesCreator by lazy { AndroidResourcesCreator(androidResourceLocation = androidResourceLocation) }

    @TaskAction
    fun createResources() {
        strings.forEach(androidResourcesCreator::createStringResourceFile)
        booleans.forEach(androidResourcesCreator::createBooleanResourceFile)
        integers.forEach(androidResourcesCreator::createIntegerResourceFile)
    }
}