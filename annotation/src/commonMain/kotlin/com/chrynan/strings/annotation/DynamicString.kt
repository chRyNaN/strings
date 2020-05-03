package com.chrynan.strings.annotation

import com.chrynan.strings.Locale

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class DynamicString(val name: String, val value: String, val locale: String = Locale.default)