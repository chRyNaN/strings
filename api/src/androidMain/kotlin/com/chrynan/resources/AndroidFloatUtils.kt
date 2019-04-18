package com.chrynan.resources

import android.content.Context
import android.util.TypedValue

fun Context.getItemId(name: String): Int = resources.getIdentifier(name, "item", packageName)

fun Context.getFloatByName(name: String): Float {
    val typedValue = TypedValue()
    resources.getValue(getItemId(name = name), typedValue, true)
    return typedValue.float
}

class AndroidFloatResourceAccessor(private val appContext: Context) : FloatResourceAccessor {

    override fun getFloat(identifier: ResourceIdentifier): Float =
        try {
            appContext.getFloatByName(name = identifier.id)
        } catch (e: Exception) {
            throw BooleanResourceNotFoundException(resourceId = identifier.id)
        }
}