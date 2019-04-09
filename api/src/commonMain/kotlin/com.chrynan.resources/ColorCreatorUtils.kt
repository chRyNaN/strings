package com.chrynan.resources

import com.chrynan.colors.Color

data class ColorResource(
    val identifier: ResourceIdentifier,
    val value: Color
)

class ColorResourcesFileBuilder(private val identifier: ResourceFileIdentifier) {

    private val colorResources = mutableSetOf<ColorResource>()

    fun color(identifier: ResourceIdentifier, value: Color) {
        colorResources += ColorResource(identifier = identifier, value = value)
    }

    fun color(identifier: ResourceIdentifier, accessor: () -> Color) {
        colorResources += ColorResource(identifier = identifier, value = accessor.invoke())
    }

    fun color(name: String, value: Color) {
        colorResources += ColorResource(identifier = NameResourceIdentifier(name = name), value = value)

    }

    fun color(name: String, accessor: () -> Color) {
        colorResources += ColorResource(identifier = NameResourceIdentifier(name = name), value = accessor.invoke())
    }

    internal fun build() = ResourceFile.ColorResourcesFile(identifier = identifier, colorResources = colorResources)
}

fun colors(fileName: String, builder: ColorResourcesFileBuilder.() -> Unit): ResourceFile.ColorResourcesFile {
    val fileBuilder = ColorResourcesFileBuilder(identifier = NameResourceFileIdentifier(name = fileName))
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}

fun colors(
    identifier: ResourceFileIdentifier,
    builder: ColorResourcesFileBuilder.() -> Unit
): ResourceFile.ColorResourcesFile {
    val fileBuilder = ColorResourcesFileBuilder(identifier = identifier)
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}