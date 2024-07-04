package com.karas.testtask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karas.testtask.db.DBContract
import com.karas.testtask.db.data.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM ${DBContract.UserEntity.TABLE_NAME}")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(userEntity: UserEntity)
}