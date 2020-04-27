package com.chrynan.resources

interface StringCache {

    fun getStringValue(resourceID: ResourceID, locale: Locale): kotlin.String

    fun getStringArray(resourceID: StringArrayResourceID, locale: Locale): Array<kotlin.String>
}