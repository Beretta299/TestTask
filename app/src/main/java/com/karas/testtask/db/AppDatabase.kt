package com.karas.testtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karas.testtask.db.dao.BootsDao
import com.karas.testtask.db.dao.UserDao
import com.karas.testtask.db.data.BootsEntity
import com.karas.testtask.db.data.UserEntity

@Database(entities = [UserEntity::class, BootsEntity::class],
    version = 1,
    exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bootsDao(): BootsDao

    abstract fun userDao(): UserDao
    companion object {
        //Migrations
    }
}