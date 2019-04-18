package com.chrynan.resources

// NOTE: Needs to be open for Gradle
open class ResourcesExtension(
    var androidResourceLocation: String,
    var iosResourceLocation: String,
    var commonGeneratedSourceLocation: String,
    var strings: Set<ResourceFile.StringResourcesFile> = emptySet(),
    var booleans: Set<ResourceFile.BooleanResourcesFile> = emptySet(),
    var integers: Set<ResourceFile.IntegerResourcesFile> = emptySet(),
    var colors: Set<ResourceFile.ColorResourcesFile> = emptySet(),
    var dimensions: Set<ResourceFile.DimensionResourcesFile> = emptySet(),
    val floats: Set<ResourceFile.FloatResourcesFile> = emptySet()
)