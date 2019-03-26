package com.chrynan.resources

interface ResourceIdentifier {

    val id: String
}

data class NameResourceIdentifier(private val name: String) : ResourceIdentifier {

    override val id = name
}

interface ResourceAccessor {

    val stringResourceAccessor: StringResourceAccessor

    val booleanResourceAccessor: BooleanResourceAccessor

    val integerResourceAccessor: IntegerResourceAccessor

    fun boolean(identifier: ResourceIdentifier) = lazy { booleanResourceAccessor.getBoolean(identifier = identifier) }

    fun boolean(name: String) =
        lazy { booleanResourceAccessor.getBoolean(identifier = NameResourceIdentifier(name = name)) }

    fun integer(identifier: ResourceIdentifier) = lazy { integerResourceAccessor.getInteger(identifier = identifier) }

    fun integer(name: String) =
        lazy { integerResourceAccessor.getInteger(identifier = NameResourceIdentifier(name = name)) }

    fun integerArray(identifier: ResourceIdentifier) =
        lazy { integerResourceAccessor.getIntegerArray(identifier = identifier) }

    fun integerArray(name: String) =
        lazy { integerResourceAccessor.getIntegerArray(identifier = NameResourceIdentifier(name = name)) }

    fun string(identifier: ResourceIdentifier) = lazy { stringResourceAccessor.getString(identifier = identifier) }

    fun string(name: String) =
        lazy { stringResourceAccessor.getString(identifier = NameResourceIdentifier(name = name)) }

    fun string(identifier: ResourceIdentifier, vararg formatArgs: Any) =
        lazy { stringResourceAccessor.getString(identifier = identifier, formatArgs = *formatArgs) }

    fun string(name: String, vararg formatArgs: Any) =
        lazy {
            stringResourceAccessor.getString(
                identifier = NameResourceIdentifier(name = name),
                formatArgs = *formatArgs
            )
        }

    fun quantityString(identifier: ResourceIdentifier, quantity: Int) =
        lazy { stringResourceAccessor.getQuantityString(identifier = identifier, quantity = quantity) }

    fun quantityString(name: String, quantity: Int) =
        lazy {
            stringResourceAccessor.getQuantityString(
                identifier = NameResourceIdentifier(name = name),
                quantity = quantity
            )
        }

    fun quantityString(identifier: ResourceIdentifier, quantity: Int, vararg formatArgs: Any) =
        lazy {
            stringResourceAccessor.getQuantityString(
                identifier = identifier,
                quantity = quantity,
                formatArgs = *formatArgs
            )
        }

    fun quantityString(name: String, quantity: Int, vararg formatArgs: Any) =
        lazy {
            stringResourceAccessor.getQuantityString(
                identifier = NameResourceIdentifier(name = name),
                quantity = quantity,
                formatArgs = *formatArgs
            )
        }

    fun stringArray(identifier: ResourceIdentifier) =
        lazy { stringResourceAccessor.getStringArray(identifier = identifier) }

    fun stringArray(name: String) =
        lazy { stringResourceAccessor.getStringArray(identifier = NameResourceIdentifier(name = name)) }
}

interface ResourceFileIdentifier {

    val fileName: String
}

data class NameResourceFileIdentifier(private val name: String) : ResourceFileIdentifier {

    override val fileName = name
}

open class ResourceNotFoundException(resourceId: String, additionalMessage: String? = null) :
    RuntimeException("Resource not found with the resourceId = $resourceId. Additional Message = $additionalMessage")

sealed class ResourceFile {

    data class StringResourcesFile(
        val identifier: ResourceFileIdentifier,
        val singleStringResources: Set<SingleStringResource>,
        val pluralStringResources: Set<PluralStringResource>,
        val stringArrayResources: Set<StringArrayResource>
    ) : ResourceFile()

    data class IntegerResourcesFile(
        val identifier: ResourceFileIdentifier,
        val integerResources: Set<IntegerResource>,
        val integerArrayResources: Set<IntegerArrayResource>
    ) : ResourceFile()

    data class BooleanResourcesFile(
        val identifier: ResourceFileIdentifier,
        val booleanResources: Set<BooleanResource>
    ) : ResourceFile()
}