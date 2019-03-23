package com.chrynan.resources

object Strings {

    lateinit var accessor: StringResourceAccessor

    fun single(identifier: StringResourceIdentifier) = accessor.getString(identifier = identifier)

    fun single(identifier: StringResourceIdentifier, vararg formatArgs: Any) =
        accessor.getString(identifier = identifier, formatArgs = *formatArgs)

    fun quantity(identifier: StringResourceIdentifier, quantity: Int) =
        accessor.getQuantityString(identifier = identifier, quantity = quantity)

    fun quantity(identifier: StringResourceIdentifier, quantity: Int, vararg formatArgs: Any) =
        accessor.getQuantityString(identifier = identifier, quantity = quantity, formatArgs = *formatArgs)

    fun array(identifier: StringResourceIdentifier) = accessor.getStringArray(identifier = identifier)
}

interface StringResourceIdentifier {

    val id: String
}

interface StringResourceAccessor {

    fun getString(identifier: StringResourceIdentifier): String

    fun getString(identifier: StringResourceIdentifier, vararg formatArgs: Any): String

    fun getQuantityString(identifier: StringResourceIdentifier, quantity: Int): String

    fun getQuantityString(identifier: StringResourceIdentifier, quantity: Int, vararg formatArgs: Any): String

    fun getStringArray(identifier: StringResourceIdentifier): Array<String>
}

class StringResourceNotFoundException(resourceId: String) :
    RuntimeException("Could not find String Resource with ID = $resourceId.")

fun string(identifier: StringResourceIdentifier) = lazy { Strings.single(identifier = identifier) }

fun string(identifier: StringResourceIdentifier, vararg formatArgs: Any) =
    lazy { Strings.single(identifier = identifier, formatArgs = *formatArgs) }

fun quantityString(identifier: StringResourceIdentifier, quantity: Int) =
    lazy { Strings.quantity(identifier = identifier, quantity = quantity) }

fun quantityString(identifier: StringResourceIdentifier, quantity: Int, vararg formatArgs: Any) =
    lazy { Strings.quantity(identifier = identifier, quantity = quantity, formatArgs = *formatArgs) }

fun stringArray(identifier: StringResourceIdentifier) = lazy { Strings.array(identifier = identifier) }