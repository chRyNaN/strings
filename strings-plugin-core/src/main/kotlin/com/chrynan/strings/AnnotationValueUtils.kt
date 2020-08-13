package com.chrynan.strings

import com.chrynan.strings.annotation.*
import com.chrynan.strings.core.StringGroupType
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor
import org.jetbrains.kotlin.fir.lightTree.converter.nameAsSafeName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.constants.ConstantValue

fun AnnotationDescriptor.asStaticString(): StaticStringAnnotationValue {
    val fieldNameName: Name = StaticString::name.name.nameAsSafeName("name")
    val fieldNameValue: Name = StaticString::value.name.nameAsSafeName("value")
    val fieldNameLocale: Name = StaticString::locale.name.nameAsSafeName("locale")

    val values: Map<Name, ConstantValue<*>> = allValueArguments

    val fieldValueName = values[fieldNameName]?.value as String
    val fieldValueValue = values[fieldNameValue]?.value as String
    val fieldValueLocale = values[fieldNameLocale]?.value as String

    return StaticStringAnnotationValue(name = fieldValueName, value = fieldValueValue, locale = fieldValueLocale)
}

fun AnnotationDescriptor.asDynamicString(): DynamicStringAnnotationValue {
    val fieldNameName: Name = DynamicString::name.name.nameAsSafeName("name")
    val fieldNameValue: Name = DynamicString::value.name.nameAsSafeName("value")
    val fieldNameLocale: Name = DynamicString::locale.name.nameAsSafeName("locale")

    val values: Map<Name, ConstantValue<*>> = allValueArguments

    val fieldValueName = values[fieldNameName]?.value as String
    val fieldValueValue = values[fieldNameValue]?.value as String
    val fieldValueLocale = values[fieldNameLocale]?.value as String

    return DynamicStringAnnotationValue(name = fieldValueName, value = fieldValueValue, locale = fieldValueLocale)
}

fun AnnotationDescriptor.asHtmlString(): HtmlStringAnnotationValue {
    val fieldNameName: Name = HtmlString::name.name.nameAsSafeName("name")
    val fieldNameValue: Name = HtmlString::value.name.nameAsSafeName("value")
    val fieldNameLocale: Name = HtmlString::locale.name.nameAsSafeName("locale")

    val values: Map<Name, ConstantValue<*>> = allValueArguments

    val fieldValueName = values[fieldNameName]?.value as String
    val fieldValueValue = values[fieldNameValue]?.value as String
    val fieldValueLocale = values[fieldNameLocale]?.value as String

    return HtmlStringAnnotationValue(name = fieldValueName, value = fieldValueValue, locale = fieldValueLocale)
}

@Suppress("UNCHECKED_CAST")
fun AnnotationDescriptor.asStringArray(): StringArrayAnnotationValue {
    val fieldNameName: Name = StringArray::name.name.nameAsSafeName("name")
    val fieldNameLocale: Name = StringArray::locale.name.nameAsSafeName("locale")
    val fieldNameValues: Name = StringArray::values.name.nameAsSafeName("values")

    val values: Map<Name, ConstantValue<*>> = allValueArguments

    val fieldValueName = values[fieldNameName]?.value as String
    val fieldValueLocale = values[fieldNameLocale]?.value as String
    val fieldValueValues = values[fieldNameValues]?.value as Array<StringArrayItem>

    return StringArrayAnnotationValue(
        name = fieldValueName,
        locale = fieldValueLocale,
        values = fieldValueValues.toList()
    )
}

@Suppress("UNCHECKED_CAST")
fun AnnotationDescriptor.asStringGroup(): StringGroupAnnotationValue {
    val fieldNameName: Name = StringGroup::name.name.nameAsSafeName("name")
    val fieldNameType: Name = StringGroup::type.name.nameAsSafeName("type")
    val fieldNameValues: Name = StringGroup::values.name.nameAsSafeName("values")

    val values: Map<Name, ConstantValue<*>> = allValueArguments

    val fieldValueName = values[fieldNameName]?.value as String
    val fieldValueType = values[fieldNameType]?.value as StringGroupType
    val fieldValueValues = values[fieldNameValues]?.value as Array<StringGroupItem>

    return StringGroupAnnotationValue(
        name = fieldValueName,
        type = fieldValueType,
        values = fieldValueValues.toList()
    )
}

@Suppress("UNCHECKED_CAST")
fun AnnotationDescriptor.asStringPlurals(): StringPluralsAnnotationValue {
    val fieldNameName: Name = StringPlurals::name.name.nameAsSafeName("name")
    val fieldNameLocale: Name = StringPlurals::locale.name.nameAsSafeName("locale")
    val fieldNameValues: Name = StringPlurals::values.name.nameAsSafeName("values")

    val values: Map<Name, ConstantValue<*>> = allValueArguments

    val fieldValueName = values[fieldNameName]?.value as String
    val fieldValueLocale = values[fieldNameLocale]?.value as String
    val fieldValueValues = values[fieldNameValues]?.value as Array<StringPluralItem>

    return StringPluralsAnnotationValue(
        name = fieldValueName,
        locale = fieldValueLocale,
        values = fieldValueValues.toList()
    )
}
