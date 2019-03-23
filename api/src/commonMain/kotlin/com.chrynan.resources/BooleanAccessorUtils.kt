package com.chrynan.resources

object Booleans {

    lateinit var accessor: BooleanAccessor

    fun boolean(identifier: ResourceIdentifier) = accessor.getBoolean(identifier = identifier)

    fun boolean(name: String) = accessor.getBoolean(identifier = NameResourceIdentifier(name = name))
}

interface BooleanAccessor {

    fun getBoolean(identifier: ResourceIdentifier): Boolean
}

class BooleanResourceNotFound(resourceId: String) : ResourceNotFoundException(resourceId = resourceId)

fun boolean(identifier: ResourceIdentifier) = lazy { Booleans.boolean(identifier = identifier) }

fun boolean(name: String) = lazy { Booleans.boolean(name = name) }