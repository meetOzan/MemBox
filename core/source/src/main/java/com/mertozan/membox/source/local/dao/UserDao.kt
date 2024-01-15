package com.mertozan.membox.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.mertozan.membox.source.local.entity.UserEntity

@Dao
interface UserDao {

    @Upsert
    fun addUserToLocal(user: UserEntity)

    @Delete
    fun deleteUserFromLocal(user: UserEntity)

    @Query("SELECT * FROM user_entity")
    fun getSingleUser(): UserEntity

    @Update
    fun updateUser(user: UserEntity)

}