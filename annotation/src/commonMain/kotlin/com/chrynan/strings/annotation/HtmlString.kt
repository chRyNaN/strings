package com.chrynan.strings.annotation

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class HtmlString(val name: String, val value: String, val locale: String = "en")