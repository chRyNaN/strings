package com.chrynan.resources

class FloatResourcesFileBuilder(private val identifier: ResourceFileIdentifier) {

    private val floatResources = mutableSetOf<FloatResource>()

    fun float(identifier: ResourceIdentifier, value: Float) {
        floatResources += FloatResource(identifier = identifier, value = value)
    }

    fun float(identifier: ResourceIdentifier, accessor: () -> Float) {
        floatResources += FloatResource(identifier = identifier, value = accessor.invoke())
    }

    fun float(name: String, value: Float) {
        floatResources += FloatResource(identifier = NameResourceIdentifier(name = name), value = value)

    }

    fun float(name: String, accessor: () -> Float) {
        floatResources += FloatResource(identifier = NameResourceIdentifier(name = name), value = accessor.invoke())
    }

    internal fun build() =
        ResourceFile.FloatResourcesFile(identifier = identifier, floatResources = floatResources)
}

fun floats(fileName: String, builder: FloatResourcesFileBuilder.() -> Unit): ResourceFile.FloatResourcesFile {
    val fileBuilder = FloatResourcesFileBuilder(identifier = NameResourceFileIdentifier(name = fileName))
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}

fun floats(
    identifier: ResourceFileIdentifier,
    builder: FloatResourcesFileBuilder.() -> Unit
): ResourceFile.FloatResourcesFile {
    val fileBuilder = FloatResourcesFileBuilder(identifier = identifier)
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}