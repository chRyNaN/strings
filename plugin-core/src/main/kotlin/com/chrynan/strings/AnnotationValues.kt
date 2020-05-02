package com.chrynan.strings

import com.chrynan.strings.annotation.*
import kotlin.reflect.KClass

sealed class AnnotationValue {

    abstract val topLevelType: TopLevelType

    enum class TopLevelType(staticKClass: KClass<out Annotation>) {
        STATIC(staticKClass = StaticString::class),
        DYNAMIC(staticKClass = DynamicString::class),
        HTML(staticKClass = HtmlString::class),
        STRING_ARRAY(staticKClass = StringArray::class),
        STRING_GROUP(staticKClass = StringGroup::class),
        STRING_PLURALS(staticKClass = StringPlurals::class)
    }
}

data class StaticStringAnnotationValue(
    val name: String,
    val value: String,
    val locale: String
) : AnnotationValue() {

    override val topLevelType: TopLevelType = TopLevelType.STATIC
}

data class DynamicStringAnnotationValue(
    val name: String,
    val value: String,
    val locale: String
) : AnnotationValue() {

    override val topLevelType: TopLevelType = TopLevelType.DYNAMIC
}

data class HtmlStringAnnotationValue(
    val name: String,
    val value: String,
    val locale: String
) : AnnotationValue() {

    override val topLevelType: TopLevelType = TopLevelType.HTML
}

data class StringArrayAnnotationValue(
    val name: String,
    val locale: String,
    val values: List<StringArrayItem> = emptyList()
) : AnnotationValue() {

    override val topLevelType: TopLevelType = TopLevelType.STRING_ARRAY
}

data class StringGroupAnnotationValue(
    val name: String,
    val type: StringGroupType = StringGroupType.STATIC,
    val values: List<StringGroupItem> = emptyList()
) : AnnotationValue() {

    override val topLevelType: TopLevelType = TopLevelType.STRING_GROUP
}

data class StringPluralsAnnotationValue(
    val name: String,
    val locale: String,
    val values: List<StringPluralItem> = emptyList()
) : AnnotationValue() {

    override val topLevelType: TopLevelType = TopLevelType.STRING_PLURALS
}