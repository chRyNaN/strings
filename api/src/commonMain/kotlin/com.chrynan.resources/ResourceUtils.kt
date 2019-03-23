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