package com.mertozan.membox.core.resource

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes id: Int) : String

    fun getDrawable(@DrawableRes id: Int) : Drawable?

}