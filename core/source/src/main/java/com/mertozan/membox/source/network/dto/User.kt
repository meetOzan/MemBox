package com.mertozan.membox.source.network.dto

data class User(
    val id : Int = 0,
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = ""
)
