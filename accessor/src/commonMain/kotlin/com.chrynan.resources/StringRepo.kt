package com.chrynan.resources

interface StringRepo {

    fun getStringValue(resourceID: ResourceID, locale: Locale): kotlin.String

    fun getPluralStringValue(resourceID: ResourceID, locale: Locale, quantity: Quantity): kotlin.String

    fun getStringArray(resourceID: StringArrayResourceID, locale: Locale): Array<kotlin.String>
}