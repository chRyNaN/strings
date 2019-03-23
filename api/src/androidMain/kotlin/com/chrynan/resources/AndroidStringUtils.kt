package com.chrynan.resources

import android.content.Context

fun Context.getStringId(name: String): Int = resources.getIdentifier(name, "string", packageName)

fun Context.getStringArrayId(name: String): Int = resources.getIdentifier(name, "array", packageName)

fun Context.getStringByName(name: String): String = getString(getStringId(name = name))

fun Context.getStringByName(name: String, vararg formatArgs: Any): String =
    getString(getStringId(name = name), formatArgs)

fun Context.getQuantifyStringByName(name: String, quantity: Int): String =
    resources.getQuantityString(getStringId(name = name), quantity)

fun Context.getQuantityStringByName(name: String, quantity: Int, vararg formatArgs: Any): String =
    resources.getQuantityString(getStringId(name = name), quantity, formatArgs)

fun Context.getStringArrayByName(name: String): Array<String> =
    resources.getStringArray(getStringArrayId(name = name))

class AndroidStringResourceAccessor(private val appContext: Context) : StringResourceAccessor {

    override fun getString(identifier: ResourceIdentifier): String =
        try {
            appContext.getStringByName(name = identifier.id)
        } catch (e: Exception) {
            throw StringResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getString(identifier: ResourceIdentifier, vararg formatArgs: Any): String =
        try {
            appContext.getStringByName(name = identifier.id, formatArgs = *formatArgs)
        } catch (e: Exception) {
            throw StringResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getQuantityString(identifier: ResourceIdentifier, quantity: Int): String =
        try {
            appContext.getQuantityStringByName(name = identifier.id, quantity = quantity)
        } catch (e: Exception) {
            throw StringResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getQuantityString(
        identifier: ResourceIdentifier,
        quantity: Int,
        vararg formatArgs: Any
    ): String =
        try {
            appContext.getQuantityStringByName(name = identifier.id, quantity = quantity, formatArgs = *formatArgs)
        } catch (e: Exception) {
            throw StringResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getStringArray(identifier: ResourceIdentifier): Array<String> =
        try {
            appContext.getStringArrayByName(name = identifier.id)
        } catch (e: Exception) {
            throw StringResourceNotFoundException(resourceId = identifier.id)
        }
}