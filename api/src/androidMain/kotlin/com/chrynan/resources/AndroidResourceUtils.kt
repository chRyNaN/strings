package com.chrynan.resources

import android.content.Context

class AndroidResourceAccessor(appContext: Context) : ResourceAccessor {

    override val stringResourceAccessor = AndroidStringResourceAccessor(appContext = appContext)
}