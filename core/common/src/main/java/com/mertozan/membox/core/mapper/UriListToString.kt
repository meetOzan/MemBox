package com.mertozan.membox.core.mapper

import android.net.Uri

fun List<Uri>.uriListToStringList(): List<String> {
    val stringList = mutableListOf<String>()
    this.forEach { uri ->
        stringList.add(uri.toString())
    }
    return stringList
}