package com.chrynan.strings.annotation

import com.chrynan.strings.Locale
import com.chrynan.strings.StringGroupType

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringGroup(
    val name: String,
    val type: StringGroupType = StringGroupType.STATIC,
    val values: Array<StringGroupItem>
)

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StringGroupItem(val value: String, val locale: String = Locale.default)