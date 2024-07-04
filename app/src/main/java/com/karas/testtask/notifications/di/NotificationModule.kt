package com.karas.testtask.notifications.di

import com.karas.testtask.data.PersonalDataManager
import com.karas.testtask.notifications.NotificationManager
import com.karas.testtask.notifications.NotificationManagerImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {
    @Provides
    @Singleton
    fun provideNotificationManager(personalDataManager: PersonalDataManager) : NotificationManager {
        return NotificationManagerImplementation(personalDataManager)
    }
}