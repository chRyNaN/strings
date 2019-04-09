package com.chrynan.resources

import android.content.Context
import com.chrynan.colors.Color
import com.chrynan.colors.ColorInt

fun Context.getColorId(name: String): Int = resources.getIdentifier(name, "color", packageName)

fun Context.getColorByName(name: String): Color = ColorInt(resources.getColor(getColorId(name = name)))

class AndroidColorResourceAccessor(private val appContext: Context) : ColorResourceAccessor {

    override fun getColor(identifier: ResourceIdentifier): Color =
        try {
            appContext.getColorByName(name = identifier.id)
        } catch (e: Exception) {
            throw ColorResourceNotFoundException(resourceId = identifier.id)
        }
}