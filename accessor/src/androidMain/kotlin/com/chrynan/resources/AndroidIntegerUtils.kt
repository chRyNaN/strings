package com.chrynan.resources

import android.content.Context

fun Context.getIntegerId(name: String): Int = resources.getIdentifier(name, "integer", packageName)

fun Context.getIntegerByName(name: String): Int = resources.getInteger(getIntegerId(name = name))

fun Context.getIntegerArrayId(name: String): Int = resources.getIdentifier(name, "integer-array", packageName)

fun Context.getIntegerArrayByName(name: String): Array<Int> =
    resources.getIntArray(getIntegerId(name = name)).toTypedArray()

class AndroidIntegerResourceAccessor(private val appContext: Context) : IntegerResourceAccessor {

    override fun getInteger(identifier: ResourceIdentifier): Int =
        try {
            appContext.getIntegerByName(name = identifier.id)
        } catch (e: Exception) {
            throw IntegerResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getIntegerArray(identifier: ResourceIdentifier): Array<Int> =
        try {
            appContext.getIntegerArrayByName(name = identifier.id)
        } catch (e: Exception) {
            throw IntegerResourceNotFoundException(resourceId = identifier.id)
        }
}