package com.chrynan.strings

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class DynamicString(val name: String, val value: String, val locale: String = "en")