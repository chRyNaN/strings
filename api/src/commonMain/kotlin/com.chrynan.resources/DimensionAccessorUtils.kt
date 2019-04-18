package com.chrynan.resources

import com.chrynan.pixel.DependencyIndependentPixels
import com.chrynan.pixel.Pixels
import com.chrynan.pixel.PointPixels
import com.chrynan.pixel.ScaledPixels

object Dimensions {

    lateinit var accessor: DimensionResourceAccessor

    fun px(identifier: ResourceIdentifier) = accessor.getPxDimension(identifier = identifier)

    fun px(name: String) = accessor.getPxDimension(identifier = NameResourceIdentifier(name = name))

    fun pt(identifier: ResourceIdentifier) = accessor.getPtDimension(identifier = identifier)

    fun pt(name: String) = accessor.getPtDimension(identifier = NameResourceIdentifier(name = name))

    fun sp(identifier: ResourceIdentifier) = accessor.getSpDimension(identifier = identifier)

    fun sp(name: String) = accessor.getSpDimension(identifier = NameResourceIdentifier(name = name))

    fun dp(identifier: ResourceIdentifier) = accessor.getDpDimension(identifier = identifier)

    fun dp(name: String) = accessor.getDpDimension(identifier = NameResourceIdentifier(name = name))
}

interface DimensionResourceAccessor {

    fun getPxDimension(identifier: ResourceIdentifier): Pixels

    fun getPtDimension(identifier: ResourceIdentifier): PointPixels

    fun getSpDimension(identifier: ResourceIdentifier): ScaledPixels

    fun getDpDimension(identifier: ResourceIdentifier): DependencyIndependentPixels
}

class DimensionResourceNotFoundException(resourceId: String) : ResourceNotFoundException(resourceId = resourceId)

fun px(identifier: ResourceIdentifier) = lazy { Dimensions.px(identifier = identifier) }

fun px(name: String) = lazy { Dimensions.px(name = name) }

fun pt(identifier: ResourceIdentifier) = lazy { Dimensions.pt(identifier = identifier) }

fun pt(name: String) = lazy { Dimensions.pt(name = name) }

fun sp(identifier: ResourceIdentifier) = lazy { Dimensions.sp(identifier = identifier) }

fun sp(name: String) = lazy { Dimensions.sp(name = name) }

fun dp(identifier: ResourceIdentifier) = lazy { Dimensions.dp(identifier = identifier) }

fun dp(name: String) = lazy { Dimensions.dp(name = name) }