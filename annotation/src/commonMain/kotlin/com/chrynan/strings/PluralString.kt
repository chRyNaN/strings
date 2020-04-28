package com.chrynan.strings

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class Plurals(val name: kotlin.String, val locale: kotlin.String, val values: Array<PluralValue>)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class PluralValue(val quantity: Quantity, val value: kotlin.String)