package com.karas.testtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karas.testtask.db.AppDatabase
import com.karas.testtask.db.data.BootsEntity
import com.karas.testtask.db.data.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val database: AppDatabase) : ViewModel() {

    init {

    }

    private val _bootEvents = MutableLiveData<List<BootsEntity>>()
    val bootEvents: LiveData<List<BootsEntity>> get() = _bootEvents

    private val _dismissAllowed = MutableLiveData<Int>()
    val dismissCount: LiveData<Int> get() = _dismissAllowed

    private val _dismissInterval = MutableLiveData<Int>()
    val dismissInterval: LiveData<Int> get() = _dismissInterval

    fun loadBootEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            _bootEvents.postValue(database.bootsDao().getAll())
        }
    }

    fun loadDismissCount() {
        viewModelScope.launch(Dispatchers.IO) {
            var user = database.userDao().getUser()
            if(user == null) {
                database.userDao().updateUser(UserEntity(1))
                user = database.userDao().getUser()
            }

            _dismissAllowed.postValue(user?.totalDismissAllowed)
        }
    }

    fun loadDismissInterval() {
        viewModelScope.launch(Dispatchers.IO) {
            var user = database.userDao().getUser()
            if(user == null) {
                database.userDao().updateUser(UserEntity(1))
                user = database.userDao().getUser()
            }

            _dismissInterval.postValue(user?.dismissIntervalValue)
        }
    }

    fun saveDismissCount(count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedDismissCount = count
            val oldUser = database.userDao().getUser()
            oldUser?.let {
                val newUser = UserEntity(it.id, count, it.dismissIntervalValue, it.dismissedTotal)
                database.userDao().updateUser(newUser)
                _dismissAllowed.postValue(count)
            }
        }
    }
}