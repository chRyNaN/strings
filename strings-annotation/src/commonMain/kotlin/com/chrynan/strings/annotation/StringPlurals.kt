package com.chrynan.strings.annotation

import com.chrynan.strings.core.Locale
import com.chrynan.strings.core.Quantity

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringPlurals(
    val name: String,
    val locale: String = Locale.default,
    val values: Array<StringPluralItem>
)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringPluralItem(val quantity: Quantity, val value: String)