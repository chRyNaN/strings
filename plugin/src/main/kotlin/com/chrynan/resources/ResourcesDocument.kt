package com.chrynan.resources

interface ResourcesDocument {

    fun addBoolean(resource: BooleanResource)

    fun addInteger(resource: IntegerResource)

    fun addIntegerArray(resource: IntegerArrayResource)

    fun addString(resource: SingleStringResource)

    fun addStringArray(resource: StringArrayResource)

    fun addPluralString(resource: PluralStringResource)

    fun addColor(resource: ColorResource)
}