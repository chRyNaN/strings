package com.chrynan.resources

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringGroup(val name: kotlin.String, val values: Array<StringValue>)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringValue(val value: kotlin.String, val locale: kotlin.String = "en")