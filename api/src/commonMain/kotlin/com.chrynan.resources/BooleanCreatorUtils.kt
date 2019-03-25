package com.chrynan.resources

data class BooleanResource(
    val identifier: ResourceIdentifier,
    val value: Boolean
)

class BooleanResourcesFileBuilder(private val identifier: ResourceFileIdentifier) {

    private val booleanResources = mutableSetOf<BooleanResource>()

    fun boolean(identifier: ResourceIdentifier, value: Boolean) {
        booleanResources += BooleanResource(identifier = identifier, value = value)
    }

    fun boolean(identifier: ResourceIdentifier, accessor: () -> Boolean) {
        booleanResources += BooleanResource(identifier = identifier, value = accessor.invoke())
    }

    fun boolean(name: String, value: Boolean) {
        booleanResources += BooleanResource(identifier = NameResourceIdentifier(name = name), value = value)

    }

    fun boolean(name: String, accessor: () -> Boolean) {
        booleanResources += BooleanResource(identifier = NameResourceIdentifier(name = name), value = accessor.invoke())
    }

    internal fun build() =
        ResourceFile.BooleanResourcesFile(identifier = identifier, booleanResources = booleanResources)
}

fun booleans(fileName: String, builder: BooleanResourcesFileBuilder.() -> Unit): ResourceFile.BooleanResourcesFile {
    val fileBuilder = BooleanResourcesFileBuilder(identifier = NameResourceFileIdentifier(name = fileName))
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}

fun booleans(
    identifier: ResourceFileIdentifier,
    builder: BooleanResourcesFileBuilder.() -> Unit
): ResourceFile.BooleanResourcesFile {
    val fileBuilder = BooleanResourcesFileBuilder(identifier = identifier)
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}