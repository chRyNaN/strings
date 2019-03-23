package com.chrynan.resources

import android.content.Context

fun Context.getBooleanId(name: String): Int = resources.getIdentifier(name, "boolean", packageName)

fun Context.getBooleanByName(name: String): Boolean = resources.getBoolean(getBooleanId(name = name))

class AndroidBooleanResourceAccessor(private val appContext: Context) : BooleanResourceAccessor {

    override fun getBoolean(identifier: ResourceIdentifier): Boolean =
        try {
            appContext.getBooleanByName(name = identifier.id)
        } catch (e: Exception) {
            throw BooleanResourceNotFoundException(resourceId = identifier.id)
        }
}