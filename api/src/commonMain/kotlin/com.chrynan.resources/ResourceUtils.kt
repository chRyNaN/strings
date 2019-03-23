package com.chrynan.resources

interface ResourceIdentifier {

    val id: String
}

interface ResourceAccessor {

    val stringResourceAccessor: StringResourceAccessor
}