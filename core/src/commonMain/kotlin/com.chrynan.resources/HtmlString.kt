package com.chrynan.resources

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class HtmlString(val name: kotlin.String, val value: kotlin.String, val locale: kotlin.String = "en")