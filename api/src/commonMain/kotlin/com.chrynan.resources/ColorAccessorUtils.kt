package com.chrynan.resources

import com.chrynan.colors.Color

data class ColorResource(
    val identifier: ResourceIdentifier,
    val value: Color
)

object Colors {

    lateinit var accessor: ColorResourceAccessor

    fun color(identifier: ResourceIdentifier) = accessor.getColor(identifier = identifier)

    fun color(name: String) = accessor.getColor(identifier = NameResourceIdentifier(name = name))
}

interface ColorResourceAccessor {

    fun getColor(identifier: ResourceIdentifier): Color
}

class ColorResourceNotFoundException(resourceId: String) : ResourceNotFoundException(resourceId = resourceId)

fun color(identifier: ResourceIdentifier) = lazy { Colors.color(identifier = identifier) }

fun color(name: String) = lazy { Colors.color(name = name) }