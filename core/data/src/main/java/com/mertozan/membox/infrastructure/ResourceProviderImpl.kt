package com.mertozan.membox.infrastructure

import android.content.Context
import com.mertozan.membox.domain.infrastructure.ResourceProvider

class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun getString(id: Int) = context.getString(id)

}