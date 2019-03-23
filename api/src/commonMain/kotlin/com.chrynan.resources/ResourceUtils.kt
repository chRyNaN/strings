package com.chrynan.resources

interface ResourceIdentifier {

    val id: String
}

data class NameResourceIdentifier(private val name: String) : ResourceIdentifier {

    override val id = name
}

interface ResourceAccessor {

    val stringResourceAccessor: StringResourceAccessor
}

interface ResourceFileIdentifier {

    val fileName: String
}

data class NameResourceFileIdentifier(private val name: String) : ResourceFileIdentifier {

    override val fileName = name
}