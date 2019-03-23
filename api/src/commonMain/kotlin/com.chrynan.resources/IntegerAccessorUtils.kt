package com.chrynan.resources

interface IntegerResourceAccessor {

    fun getInteger(identifier: ResourceIdentifier): Int

    fun getIntegerArray(identifier: ResourceIdentifier): Array<Int>
}

class IntegerResourceNotFoundException(resourceId: String) : ResourceNotFoundException(resourceId = resourceId)

object Integers {

    lateinit var accessor: IntegerResourceAccessor

    fun integer(identifier: ResourceIdentifier) = accessor.getInteger(identifier = identifier)

    fun integer(name: String) = accessor.getInteger(identifier = NameResourceIdentifier(name = name))

    fun integerArray(identifier: ResourceIdentifier) = accessor.getIntegerArray(identifier = identifier)

    fun integerArray(name: String) = accessor.getIntegerArray(identifier = NameResourceIdentifier(name = name))
}

fun integer(identifier: ResourceIdentifier) = lazy { Integers.integer(identifier = identifier) }

fun integer(name: String) = lazy { Integers.integer(name = name) }

fun integerArray(identifier: ResourceIdentifier) = lazy { Integers.integerArray(identifier = identifier) }

fun integerArray(name: String) = lazy { Integers.integerArray(name = name) }