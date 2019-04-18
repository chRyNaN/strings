package com.chrynan.resources

import org.gradle.api.Plugin
import org.gradle.api.Project

class ResourcesPlugin : Plugin<Project> {

    companion object {

        private const val TASK_NAME = "createResources"
        private const val EXTENSION_NAME = "resources"
    }

    override fun apply(project: Project) {
        val resourcesExtension = project.extensions.create(EXTENSION_NAME, ResourcesExtension::class.java)

        project.tasks.create(TASK_NAME, CreateResourcesTask::class.java) {
            it.androidResourceLocation = resourcesExtension.androidResourceLocation
            it.iosResourceLocation = resourcesExtension.iosResourceLocation
            it.commonGeneratedSourceLocation = resourcesExtension.commonGeneratedSourceLocation
            it.booleans = resourcesExtension.booleans
            it.colors = resourcesExtension.colors
            it.integers = resourcesExtension.integers
            it.strings = resourcesExtension.strings
            it.dimensions = resourcesExtension.dimensions
            it.floats = resourcesExtension.floats
        }

        project.task("assemble").dependsOn(TASK_NAME)
    }
}