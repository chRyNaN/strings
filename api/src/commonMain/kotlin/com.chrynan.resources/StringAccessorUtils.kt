package com.chrynan.resources

object Strings {

    lateinit var accessor: StringResourceAccessor

    fun single(identifier: ResourceIdentifier) = accessor.getString(identifier = identifier)

    fun single(name: String) = accessor.getString(identifier = NameResourceIdentifier(name = name))

    fun single(identifier: ResourceIdentifier, vararg formatArgs: Any) =
        accessor.getString(identifier = identifier, formatArgs = *formatArgs)

    fun single(name: String, vararg formatArgs: Any) =
        accessor.getString(identifier = NameResourceIdentifier(name = name), formatArgs = *formatArgs)

    fun quantity(identifier: ResourceIdentifier, quantity: Int) =
        accessor.getQuantityString(identifier = identifier, quantity = quantity)

    fun quantity(name: String, quantity: Int) =
        accessor.getQuantityString(identifier = NameResourceIdentifier(name = name), quantity = quantity)

    fun quantity(identifier: ResourceIdentifier, quantity: Int, vararg formatArgs: Any) =
        accessor.getQuantityString(
            identifier = identifier,
            quantity = quantity,
            formatArgs = *formatArgs
        )

    fun quantity(name: String, quantity: Int, vararg formatArgs: Any) =
        accessor.getQuantityString(
            identifier = NameResourceIdentifier(name = name),
            quantity = quantity,
            formatArgs = *formatArgs
        )

    fun array(identifier: ResourceIdentifier) = accessor.getStringArray(identifier = identifier)

    fun array(name: String) = accessor.getStringArray(identifier = NameResourceIdentifier(name = name))
}

interface StringResourceAccessor {

    fun getString(identifier: ResourceIdentifier): String

    fun getString(identifier: ResourceIdentifier, vararg formatArgs: Any): String

    fun getQuantityString(identifier: ResourceIdentifier, quantity: Int): String

    fun getQuantityString(identifier: ResourceIdentifier, quantity: Int, vararg formatArgs: Any): String

    fun getStringArray(identifier: ResourceIdentifier): Array<String>
}

class StringResourceNotFoundException(resourceId: String) : ResourceNotFoundException(resourceId = resourceId)

fun string(identifier: ResourceIdentifier) = lazy { Strings.single(identifier = identifier) }

fun string(name: String) = lazy { Strings.single(name = name) }

fun string(identifier: ResourceIdentifier, vararg formatArgs: Any) =
    lazy { Strings.single(identifier = identifier, formatArgs = *formatArgs) }

fun string(name: String, vararg formatArgs: Any) =
    lazy { Strings.single(name = name, formatArgs = *formatArgs) }

fun quantityString(identifier: ResourceIdentifier, quantity: Int) =
    lazy { Strings.quantity(identifier = identifier, quantity = quantity) }

fun quantityString(name: String, quantity: Int) =
    lazy { Strings.quantity(name = name, quantity = quantity) }

fun quantityString(identifier: ResourceIdentifier, quantity: Int, vararg formatArgs: Any) =
    lazy { Strings.quantity(identifier = identifier, quantity = quantity, formatArgs = *formatArgs) }

fun quantityString(name: String, quantity: Int, vararg formatArgs: Any) =
    lazy { Strings.quantity(name = name, quantity = quantity, formatArgs = *formatArgs) }

fun stringArray(identifier: ResourceIdentifier) = lazy { Strings.array(identifier = identifier) }

fun stringArray(name: String) = lazy { Strings.array(name = name) }