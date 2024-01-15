package com.mertozan.membox.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_entity")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("user_id")
    val userId: Int,

    @ColumnInfo("user_name")
    val userName: String,

    @ColumnInfo("user_surname")
    val userSurname: String,

    @ColumnInfo("user_email")
    val userEmail: String,

    @ColumnInfo("user_password")
    val userPassword: String,

)
