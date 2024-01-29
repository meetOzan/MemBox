package com.mertozan.membox.domain.infrastructure

import androidx.annotation.StringRes

fun interface ResourceProvider {

    fun getString(@StringRes id: Int): String

}