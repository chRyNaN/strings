package com.chrynan.resources

object Strings {

    lateinit var accessor: StringResourceAccessor

    fun single(identifier: ResourceIdentifier) = accessor.getString(identifier = identifier)

    fun single(identifier: ResourceIdentifier, vararg formatArgs: Any) =
        accessor.getString(identifier = identifier, formatArgs = *formatArgs)

    fun quantity(identifier: ResourceIdentifier, quantity: Int) =
        accessor.getQuantityString(identifier = identifier, quantity = quantity)

    fun quantity(identifier: ResourceIdentifier, quantity: Int, vararg formatArgs: Any) =
        accessor.getQuantityString(identifier = identifier, quantity = quantity, formatArgs = *formatArgs)

    fun array(identifier: ResourceIdentifier) = accessor.getStringArray(identifier = identifier)
}

interface ResourceIdentifier {

    val id: String
}

interface StringResourceAccessor {

    fun getString(identifier: ResourceIdentifier): String

    fun getString(identifier: ResourceIdentifier, vararg formatArgs: Any): String

    fun getQuantityString(identifier: ResourceIdentifier, quantity: Int): String

    fun getQuantityString(identifier: ResourceIdentifier, quantity: Int, vararg formatArgs: Any): String

    fun getStringArray(identifier: ResourceIdentifier): Array<String>
}

class StringResourceNotFoundException(resourceId: String) :
    RuntimeException("Could not find String Resource with ID = $resourceId.")

fun string(identifier: ResourceIdentifier) = lazy { Strings.single(identifier = identifier) }

fun string(identifier: ResourceIdentifier, vararg formatArgs: Any) =
    lazy { Strings.single(identifier = identifier, formatArgs = *formatArgs) }

fun quantityString(identifier: ResourceIdentifier, quantity: Int) =
    lazy { Strings.quantity(identifier = identifier, quantity = quantity) }

fun quantityString(identifier: ResourceIdentifier, quantity: Int, vararg formatArgs: Any) =
    lazy { Strings.quantity(identifier = identifier, quantity = quantity, formatArgs = *formatArgs) }

fun stringArray(identifier: ResourceIdentifier) = lazy { Strings.array(identifier = identifier) }