package com.karas.testtask.data

import com.karas.testtask.db.dao.UserDao

interface PersonalDataManager {
    suspend fun getIntervalData(): Long
    suspend fun onDismissed()
}

class PersonalDataManagerImplementation(val userDao: UserDao): PersonalDataManager {
    override suspend fun getIntervalData(): Long {
        var interval: Long = 15
        val user = userDao.getUser()

        if(user != null) {
            interval = if(user.dismissedTotal == user.totalDismissAllowed) {
                15
            }else if((user.dismissedTotal < user.totalDismissAllowed) && (user.dismissedTotal > 0)) {
                user.dismissedTotal.toLong() * user.dismissIntervalValue
            } else {
                15
            }
        }


        return interval
    }

    override suspend fun onDismissed() {
        val user = userDao.getUser()
        if(user != null) {
            user.dismissedTotal = ++user.dismissedTotal?:0
            if(user.dismissedTotal > user.totalDismissAllowed) {
                user.dismissedTotal = 0
            }
            userDao.updateUser(user)
        }
    }

}