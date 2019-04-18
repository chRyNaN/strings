package com.chrynan.resources

import com.chrynan.pixel.*

data class DimensionResource(
    val identifier: ResourceIdentifier,
    val value: ScreenDimensionUnit
)

class DimensionResourcesFileBuilder(private val identifier: ResourceFileIdentifier) {

    private val dimensionResources = mutableSetOf<DimensionResource>()

    fun px(identifier: ResourceIdentifier, value: Pixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = value)
    }

    fun px(identifier: ResourceIdentifier, accessor: () -> Pixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = accessor.invoke())
    }

    fun px(name: String, value: Pixels) {
        dimensionResources += DimensionResource(identifier = NameResourceIdentifier(name = name), value = value)
    }

    fun px(name: String, accessor: () -> Pixels) {
        dimensionResources += DimensionResource(
            identifier = NameResourceIdentifier(name = name),
            value = accessor.invoke()
        )
    }

    fun sp(identifier: ResourceIdentifier, value: ScaledPixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = value)
    }

    fun sp(identifier: ResourceIdentifier, accessor: () -> ScaledPixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = accessor.invoke())
    }

    fun sp(name: String, value: ScaledPixels) {
        dimensionResources += DimensionResource(identifier = NameResourceIdentifier(name = name), value = value)
    }

    fun sp(name: String, accessor: () -> ScaledPixels) {
        dimensionResources += DimensionResource(
            identifier = NameResourceIdentifier(name = name),
            value = accessor.invoke()
        )
    }

    fun dp(identifier: ResourceIdentifier, value: DependencyIndependentPixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = value)
    }

    fun dp(identifier: ResourceIdentifier, accessor: () -> DependencyIndependentPixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = accessor.invoke())
    }

    fun dp(name: String, value: DependencyIndependentPixels) {
        dimensionResources += DimensionResource(identifier = NameResourceIdentifier(name = name), value = value)
    }

    fun dp(name: String, accessor: () -> DependencyIndependentPixels) {
        dimensionResources += DimensionResource(
            identifier = NameResourceIdentifier(name = name),
            value = accessor.invoke()
        )
    }

    fun pt(identifier: ResourceIdentifier, value: PointPixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = value)
    }

    fun pt(identifier: ResourceIdentifier, accessor: () -> PointPixels) {
        dimensionResources += DimensionResource(identifier = identifier, value = accessor.invoke())
    }

    fun pt(name: String, value: PointPixels) {
        dimensionResources += DimensionResource(identifier = NameResourceIdentifier(name = name), value = value)
    }

    fun pt(name: String, accessor: () -> PointPixels) {
        dimensionResources += DimensionResource(identifier = NameResourceIdentifier(name = name), value = accessor.invoke())
    }

    internal fun build() =
        ResourceFile.DimensionResourcesFile(identifier = identifier, dimensionResources = dimensionResources)
}

fun dimensions(
    fileName: String,
    builder: DimensionResourcesFileBuilder.() -> Unit
): ResourceFile.DimensionResourcesFile {
    val fileBuilder = DimensionResourcesFileBuilder(identifier = NameResourceFileIdentifier(name = fileName))
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}

fun dimensions(
    identifier: ResourceFileIdentifier,
    builder: DimensionResourcesFileBuilder.() -> Unit
): ResourceFile.DimensionResourcesFile {
    val fileBuilder = DimensionResourcesFileBuilder(identifier = identifier)
    builder.invoke(fileBuilder)
    return fileBuilder.build()
}