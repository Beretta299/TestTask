package com.karas.testtask.data.di

import com.karas.testtask.data.PersonalDataManager
import com.karas.testtask.data.PersonalDataManagerImplementation
import com.karas.testtask.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersonalDataModule{
    @Provides
    @Singleton
    fun providePersonalDataModule(appDatabase: AppDatabase): PersonalDataManager {
        return PersonalDataManagerImplementation(appDatabase.userDao())
    }
}