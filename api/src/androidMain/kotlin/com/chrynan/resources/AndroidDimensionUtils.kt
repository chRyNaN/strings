package com.chrynan.resources

import android.content.Context
import com.chrynan.pixel.*

fun Context.getDimensionId(name: String): Int = resources.getIdentifier(name, "dimension", packageName)

fun Context.getDimensionByName(name: String): Float = resources.getDimension(getDimensionId(name = name))

class AndroidDimensionResourceAccessor(private val appContext: Context) : DimensionResourceAccessor {

    init {
        Pixel.converter = ContextScreenDimensionUnitConverter(context = appContext)
    }

    override fun getPxDimension(identifier: ResourceIdentifier) =
        try {
            px(appContext.getDimensionByName(name = identifier.id))
        } catch (e: Exception) {
            throw DimensionResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getPtDimension(identifier: ResourceIdentifier) =
        try {
            pt(appContext.getDimensionByName(name = identifier.id))
        } catch (e: Exception) {
            throw DimensionResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getSpDimension(identifier: ResourceIdentifier) =
        try {
            sp(appContext.getDimensionByName(name = identifier.id))
        } catch (e: Exception) {
            throw DimensionResourceNotFoundException(resourceId = identifier.id)
        }

    override fun getDpDimension(identifier: ResourceIdentifier) =
        try {
            dip(appContext.getDimensionByName(name = identifier.id))
        } catch (e: Exception) {
            throw DimensionResourceNotFoundException(resourceId = identifier.id)
        }
}