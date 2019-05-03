package com.chrynan.resources

class IntegerResourcesFileBuilder(private val identifier: ResourceFileIdentifier) {

    private val integerResources = mutableSetOf<IntegerResource>()
    private val integerArrayResources = mutableSetOf<IntegerArrayResource>()

    fun integer(identifier: ResourceIdentifier, value: Int) {
        integerResources += IntegerResource(identifier = identifier, value = value)
    }

    fun integer(identifier: ResourceIdentifier, accessor: () -> Int) {
        integerResources += IntegerResource(identifier = identifier, value = accessor.invoke())
    }

    fun integer(name: String, value: Int) {
        integerResources += IntegerResource(identifier = NameResourceIdentifier(name = name), value = value)
    }

    fun integer(name: String, accessor: () -> Int) {
        integerResources += IntegerResource(identifier = NameResourceIdentifier(name = name), value = accessor.invoke())
    }

    fun array(identifier: ResourceIdentifier, builder: IntegerArrayBuilder.() -> Unit) {
        val arrayBuilder = IntegerArrayBuilder(identifier = identifier)
        builder.invoke(arrayBuilder)
        integerArrayResources += arrayBuilder.build()
    }

    fun array(name: String, builder: IntegerArrayBuilder.() -> Unit) {
        val arrayBuilder = IntegerArrayBuilder(identifier = NameResourceIdentifier(name = name))
        builder.invoke(arrayBuilder)
        integerArrayResources += arrayBuilder.build()
    }

    internal fun build() = ResourceFile.IntegerResourcesFile(
        identifier = identifier,
        integerResources = integerResources,
        integerArrayResources = integerArrayResources
    )
}

class IntegerArrayBuilder(private val identifier: ResourceIdentifier) {

    private val arrayValues = mutableListOf<Int>()

    fun item(value: Int) {
        arrayValues += value
    }

    fun item(accessor: () -> Int) {
        arrayValues += accessor.invoke()
    }

    internal fun build() = IntegerArrayResource(identifier = identifier, values = arrayValues)
}

fun integers(
    identifier: ResourceFileIdentifier,
    builder: IntegerResourcesFileBuilder.() -> Unit
): ResourceFile.IntegerResourcesFile {
    val fileBuilder = IntegerResourcesFileBuilder(identifier = identifier)
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}

fun integers(name: String, builder: IntegerResourcesFileBuilder.() -> Unit): ResourceFile.IntegerResourcesFile {
    val fileBuilder = IntegerResourcesFileBuilder(identifier = NameResourceFileIdentifier(name = name))
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}