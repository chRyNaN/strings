package com.chrynan.resources.creator

import com.chrynan.resources.ResourceFile

interface ResourcesCreator {

    fun createStringResourceFile(resourceFile: ResourceFile.StringResourcesFile)

    fun createBooleanResourceFile(resourceFile: ResourceFile.BooleanResourcesFile)

    fun createIntegerResourceFile(resourceFile: ResourceFile.IntegerResourcesFile)

    fun createColorResourceFile(resourceFile: ResourceFile.ColorResourcesFile)
}