package com.chrynan.strings.annotation

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringArray(
    val name: String,
    val locale: String = "en",
    val values: Array<StringArrayItem>
)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringArrayItem(val value: String)