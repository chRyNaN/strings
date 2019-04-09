package com.chrynan.resources

import android.content.Context

class AndroidResourceAccessor(appContext: Context) : ResourceAccessor {

    override val stringResourceAccessor = AndroidStringResourceAccessor(appContext = appContext)

    override val booleanResourceAccessor = AndroidBooleanResourceAccessor(appContext = appContext)

    override val integerResourceAccessor = AndroidIntegerResourceAccessor(appContext = appContext)

    override val colorResourceAccessor = AndroidColorResourceAccessor(appContext = appContext)
}