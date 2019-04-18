package com.chrynan.resources

object Floats {

    lateinit var accessor: FloatResourceAccessor

    fun float(identifier: ResourceIdentifier) = accessor.getFloat(identifier = identifier)

    fun float(name: String) = accessor.getFloat(identifier = NameResourceIdentifier(name = name))
}

interface FloatResourceAccessor {

    fun getFloat(identifier: ResourceIdentifier): Float
}

class FloatResourceNotFoundException(resourceId: String) : ResourceNotFoundException(resourceId = resourceId)

fun float(identifier: ResourceIdentifier) = lazy { Floats.float(identifier = identifier) }

fun float(name: String) = lazy { Floats.float(name = name) }