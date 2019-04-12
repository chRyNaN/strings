package com.chrynan.resources

import org.gradle.api.Plugin
import org.gradle.api.Project

class ResourcesPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val resourcesExtension = project.extensions.create("resources", ResourcesExtension::class.java)

        project.tasks.create("createResources", CreateResourcesTask::class.java) {
            it.androidResourceLocation = resourcesExtension.androidResourceLocation
            it.iosResourceLocation = resourcesExtension.iosResourceLocation
            it.commonGeneratedSourceLocation = resourcesExtension.commonGeneratedSourceLocation
            it.booleans = resourcesExtension.booleans
            it.colors = resourcesExtension.colors
            it.integers = resourcesExtension.integers
            it.strings = resourcesExtension.strings
        }

        project.task("assemble").dependsOn("createResources")
    }
}