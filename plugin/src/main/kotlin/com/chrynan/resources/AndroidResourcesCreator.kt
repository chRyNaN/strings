package com.chrynan.resources

class AndroidResourcesCreator {

    fun create(resourceFiles: List<ResourceFile>) = resourceFiles.forEach {
        when (it) {
            is ResourceFile.StringResourcesFile -> createStringResourceFile(it)
            is ResourceFile.BooleanResourcesFile -> createBooleanResourceFile(it)
            is ResourceFile.IntegerResourcesFile -> createIntegerResourceFile(it)
        }
    }

    private fun createStringResourceFile(resourceFile: ResourceFile.StringResourcesFile) {

    }

    private fun createBooleanResourceFile(resourceFile: ResourceFile.BooleanResourcesFile) {

    }

    private fun createIntegerResourceFile(resourceFile: ResourceFile.IntegerResourcesFile) {

    }
}