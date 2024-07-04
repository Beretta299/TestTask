package com.karas.testtask.db.di

import android.app.Application
import androidx.room.Room
import com.karas.testtask.db.AppDatabase
import com.karas.testtask.db.dao.BootsDao
import com.karas.testtask.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
    ) = Room.databaseBuilder(app, AppDatabase::class.java, "boots_db")
        .build()

    @Provides
    fun provideModelDao(appDatabase: AppDatabase): BootsDao {
        return appDatabase.bootsDao();
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) : UserDao {
        return appDatabase.userDao()
    }
}