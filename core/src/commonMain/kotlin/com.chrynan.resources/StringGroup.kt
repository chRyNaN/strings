package com.chrynan.resources

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringGroup(val name: kotlin.String, val values: Array<StringGroupItem>)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringGroupItem(val value: kotlin.String, val locale: kotlin.String = "en")