package com.karas.testtask.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.karas.testtask.db.DBContract
import com.karas.testtask.db.data.BootsEntity

@Dao
interface BootsDao {
    @Insert
    suspend fun insert(bootEvent: BootsEntity)

    @Query("SELECT * FROM ${DBContract.BootsEntity.TABLE_NAME} ORDER BY timestamp DESC")
    suspend fun getAll(): List<BootsEntity>
}