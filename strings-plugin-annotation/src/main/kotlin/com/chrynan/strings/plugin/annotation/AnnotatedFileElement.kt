package com.chrynan.strings.plugin.annotation

data class AnnotatedFileElement(
    val nameAndLocation: FileNameAndLocation,
    val staticStrings: Set<StaticStringAnnotationValue> = emptySet(),
    val dynamicStrings: Set<DynamicStringAnnotationValue> = emptySet(),
    val htmlStrings: Set<HtmlStringAnnotationValue> = emptySet(),
    val stringArrays: Set<StringArrayAnnotationValue> = emptySet(),
    val stringGroups: Set<StringGroupAnnotationValue> = emptySet(),
    val stringPlurals: Set<StringPluralsAnnotationValue> = emptySet()
)