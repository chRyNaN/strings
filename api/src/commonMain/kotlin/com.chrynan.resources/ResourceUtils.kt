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