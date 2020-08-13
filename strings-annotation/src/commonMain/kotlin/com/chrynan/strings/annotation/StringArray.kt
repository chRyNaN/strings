package com.chrynan.strings.annotation

import com.chrynan.strings.core.Locale

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringArray(
    val name: String,
    val locale: String = Locale.default,
    val values: Array<StringArrayItem>
)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringArrayItem(val value: String)