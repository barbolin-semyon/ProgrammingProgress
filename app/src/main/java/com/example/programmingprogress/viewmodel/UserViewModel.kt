package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingprogress.model.entities.User
import com.example.programmingprogress.model.firebase.AuthDataSource
import com.example.programmingprogress.model.firebase.UserDataSource
import com.example.programmingprogress.util.getDayOfYear
import com.example.programmingprogress.util.parseCalendar
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class UserViewModel : ViewModel() {
    val userDataSource = UserDataSource
    val authDataSource = AuthDataSource

    private val _isRequestSuccess = MutableLiveData(false)
    val isRequestSuccess: LiveData<Boolean>
        get() = _isRequestSuccess

    private val _isRequestError = MutableLiveData("")
    val isRequestError: LiveData<String>
        get() = _isRequestError

    private val _userInfo by lazy { MutableLiveData(User()) }
    val userInfo: LiveData<User>
        get() = _userInfo

    private val _users by lazy { MutableLiveData<List<User>>() }
    val users: LiveData<List<User>>
        get() = _users

    fun updateUserValueOfDay(countHours: Double) = viewModelScope.launch {
        userInfo.value!!.apply {
            async {
                userDataSource.updateCountOfDaysAndHourSuccess(
                    userId = id,
                    countOfDaysSuccess + 1,
                    hours = hours + countHours
                )
            }.await()

            _isRequestSuccess.value = true
        }
    }

    fun getInformationUser() = viewModelScope.launch {
        val id = authDataSource.getUserId()
        if (id != null) {
            userDataSource.getInformationUser(id)
                .addOnSuccessListener {
                    _userInfo.value = it.toObject(User::class.java)
                }
                .addOnFailureListener {
                    _isRequestError.value = it.message
                }
        } else {
            _isRequestError.value = "not authorization"
        }
    }

    fun getUsersWithSortedByScore() = viewModelScope.launch {
        userDataSource.getUsers().addOnSuccessListener { result ->
            val tempList = result.toObjects(User::class.java)
            tempList.sortedBy { it.countOfDaysSuccess + it.countOfConsecutiveDaysSuccess * 3 + it.hours }
            _users.value = tempList
        }
    }

    fun updateCountConsecutiveDays(newCount: Int) = viewModelScope.launch {
        userDataSource.updateCountOfConsecutiveDays(authDataSource.getUserId()!!, newCount)
            .addOnSuccessListener {
                _isRequestSuccess.value = true
            }
    }
}