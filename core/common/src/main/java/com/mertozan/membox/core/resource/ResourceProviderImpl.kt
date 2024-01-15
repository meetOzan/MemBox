package com.mertozan.membox.core.resource

import android.content.Context
import android.graphics.drawable.Drawable

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun getString(id: Int) = context.getString(id)

    override fun getDrawable(id: Int): Drawable? = context.getDrawable(id)

}