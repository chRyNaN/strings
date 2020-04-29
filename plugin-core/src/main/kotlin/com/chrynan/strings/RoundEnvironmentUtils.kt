package com.chrynan.strings

import de.jensklingenberg.mpapt.model.RoundEnvironment
import kotlin.reflect.KClass

fun <A : Annotation> RoundEnvironment.getElementsAnnotatedWith(kclass: KClass<A>) =
    getElementsAnnotatedWith(kclass.java.name)

fun RoundEnvironment.mapStaticStringsTo(map: MutableMap<FileNameAndLocation, AnnotatedFileElement>) {
    getElementsAnnotatedWith(StaticString::class)
        .associateAllBy(
            keySelector = { it.fileNameAndLocation() },
            valueSelector = { it.annotation!!.asStaticString() })
        .assignEachTo(map) { key, thisValue, mapValue ->
            mapValue?.copy(staticStrings = mapValue.staticStrings + thisValue)
                ?: AnnotatedFileElement(nameAndLocation = key, staticStrings = thisValue)
        }
}

fun RoundEnvironment.mapDynamicStringsTo(map: MutableMap<FileNameAndLocation, AnnotatedFileElement>) {
    getElementsAnnotatedWith(DynamicString::class)
        .associateAllBy(
            keySelector = { it.fileNameAndLocation() },
            valueSelector = { it.annotation!!.asDynamicString() })
        .assignEachTo(map) { key, thisValue, mapValue ->
            mapValue?.copy(dynamicStrings = mapValue.dynamicStrings + thisValue)
                ?: AnnotatedFileElement(nameAndLocation = key, dynamicStrings = thisValue)
        }
}

fun RoundEnvironment.mapHtmlStringsTo(map: MutableMap<FileNameAndLocation, AnnotatedFileElement>) {
    getElementsAnnotatedWith(HtmlString::class)
        .associateAllBy(
            keySelector = { it.fileNameAndLocation() },
            valueSelector = { it.annotation!!.asHtmlString() })
        .assignEachTo(map) { key, thisValue, mapValue ->
            mapValue?.copy(htmlStrings = mapValue.htmlStrings + thisValue)
                ?: AnnotatedFileElement(nameAndLocation = key, htmlStrings = thisValue)
        }
}

fun RoundEnvironment.mapStringArraysTo(map: MutableMap<FileNameAndLocation, AnnotatedFileElement>) {
    getElementsAnnotatedWith(StringArray::class)
        .associateAllBy(
            keySelector = { it.fileNameAndLocation() },
            valueSelector = { it.annotation!!.asStringArray() })
        .assignEachTo(map) { key, thisValue, mapValue ->
            mapValue?.copy(stringArrays = mapValue.stringArrays + thisValue)
                ?: AnnotatedFileElement(nameAndLocation = key, stringArrays = thisValue)
        }
}

fun RoundEnvironment.mapStringGroupsTo(map: MutableMap<FileNameAndLocation, AnnotatedFileElement>) {
    getElementsAnnotatedWith(StringGroup::class)
        .associateAllBy(
            keySelector = { it.fileNameAndLocation() },
            valueSelector = { it.annotation!!.asStringGroup() })
        .assignEachTo(map) { key, thisValue, mapValue ->
            mapValue?.copy(stringGroups = mapValue.stringGroups + thisValue)
                ?: AnnotatedFileElement(nameAndLocation = key, stringGroups = thisValue)
        }
}

fun RoundEnvironment.mapStringPluralsTo(map: MutableMap<FileNameAndLocation, AnnotatedFileElement>) {
    getElementsAnnotatedWith(StringPlurals::class)
        .associateAllBy(
            keySelector = { it.fileNameAndLocation() },
            valueSelector = { it.annotation!!.asStringPlurals() })
        .assignEachTo(map) { key, thisValue, mapValue ->
            mapValue?.copy(stringPlurals = mapValue.stringPlurals + thisValue)
                ?: AnnotatedFileElement(nameAndLocation = key, stringPlurals = thisValue)
        }
}