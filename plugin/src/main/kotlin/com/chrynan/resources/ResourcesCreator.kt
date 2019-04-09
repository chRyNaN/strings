package com.chrynan.resources

interface ResourcesCreator {

    fun createStringResourceFile(resourceFile: ResourceFile.StringResourcesFile)

    fun createBooleanResourceFile(resourceFile: ResourceFile.BooleanResourcesFile)

    fun createIntegerResourceFile(resourceFile: ResourceFile.IntegerResourcesFile)

    fun createColorResourceFile(resourceFile: ResourceFile.ColorResourcesFile)
}