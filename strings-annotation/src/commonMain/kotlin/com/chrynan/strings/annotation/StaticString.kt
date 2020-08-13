package com.chrynan.strings.annotation

import com.chrynan.strings.core.Locale

/**
 * String
 * Plurals
 * Quantity
 * Array
 * Arguments
 *
 * Generated class can be prefaced with the file name perhaps. This would allow separating string resources into multiple
 * files and would make sense why there's a File Annotation Target. But need to think about how this works with different
 * locales.
 **/

@Repeatable
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class StaticString(val name: String, val value: String, val locale: String = Locale.default)