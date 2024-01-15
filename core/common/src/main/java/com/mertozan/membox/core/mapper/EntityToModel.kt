package com.mertozan.membox.core.mapper

fun <T, R> List<T>.mapList(mapper: (T) -> R): List<R> {
    return map(mapper)
}

fun <T,R> T.mapModel(mapper: (T) -> R): R {
    return mapper(this)
}