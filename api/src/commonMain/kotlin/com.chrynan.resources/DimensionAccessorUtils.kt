package com.chrynan.resources

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

    fun getPxDimension(identifier: ResourceIdentifier): Float // TODO return the appropriate dimension values

    fun getPtDimension(identifier: ResourceIdentifier): Float

    fun getSpDimension(identifier: ResourceIdentifier): Float

    fun getDpDimension(identifier: ResourceIdentifier): Float
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