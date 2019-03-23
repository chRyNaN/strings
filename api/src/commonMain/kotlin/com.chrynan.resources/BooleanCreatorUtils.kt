package com.chrynan.resources

data class BooleanResourcesFile(
    val fileName: String,
    val booleanResources: Set<BooleanResource>
)

data class BooleanResource(
    val identifier: ResourceIdentifier,
    val value: Boolean
)

class BooleanResourcesFileBuilder(private val fileName: String) {

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

    fun build() = BooleanResourcesFile(fileName = fileName, booleanResources = booleanResources)
}

fun booleans(fileName: String, builder: BooleanResourcesFileBuilder.() -> Unit): BooleanResourcesFile {
    val fileBuilder = BooleanResourcesFileBuilder(fileName = fileName)
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}