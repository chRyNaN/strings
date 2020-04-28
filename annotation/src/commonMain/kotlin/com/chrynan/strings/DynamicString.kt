package com.chrynan.strings

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class DynamicString(val name: kotlin.String, val value: kotlin.String, val locale: kotlin.String = "en")