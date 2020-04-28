package com.chrynan.strings

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringArray(
    val name: kotlin.String,
    val locale: kotlin.String = "en",
    val values: Array<StringArrayItem>
)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringArrayItem(val value: kotlin.String)