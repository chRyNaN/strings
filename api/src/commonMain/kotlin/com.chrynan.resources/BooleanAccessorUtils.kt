package com.chrynan.resources

object Booleans {

    lateinit var accessor: BooleanResourceAccessor

    fun boolean(identifier: ResourceIdentifier) = accessor.getBoolean(identifier = identifier)

    fun boolean(name: String) = accessor.getBoolean(identifier = NameResourceIdentifier(name = name))
}

interface BooleanResourceAccessor {

    fun getBoolean(identifier: ResourceIdentifier): Boolean
}

class BooleanResourceNotFoundException(resourceId: String) : ResourceNotFoundException(resourceId = resourceId)

fun boolean(identifier: ResourceIdentifier) = lazy { Booleans.boolean(identifier = identifier) }

fun boolean(name: String) = lazy { Booleans.boolean(name = name) }