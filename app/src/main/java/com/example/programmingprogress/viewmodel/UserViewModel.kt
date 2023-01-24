package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingprogress.model.entities.User
import com.example.programmingprogress.model.firebase.HistoryDataSource
import com.example.programmingprogress.model.firebase.UserDataSource
import kotlinx.coroutines.launch

class UserViewModel() : ViewModel() {
    private val authDataSource = UserDataSource

    private val _isRequestSuccess = MutableLiveData(false)
    val isRequestSuccess: LiveData<Boolean>
        get() = _isRequestSuccess

    private val _isRequestError = MutableLiveData("")
    val isRequestError: LiveData<String>
        get() = _isRequestError

    private val _userInfo by lazy { MutableLiveData(User()) }
    val userInfo: LiveData<User>
        get() = _userInfo

    fun isAuthorization() = authDataSource.getUserId() != null

    fun getInformationUser() = viewModelScope.launch {
        if (authDataSource.getUserId() != null) {
            authDataSource.getInformationUser()
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
    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            authDataSource.signInWithEmail(email, password).addOnCompleteListener { task ->
                with(task) {
                    addOnSuccessListener {
                        _isRequestSuccess.value = true
                    }
                    addOnFailureListener {
                        _isRequestError.value = it.message
                    }
                }
            }
        }
    }

    fun signOut() = viewModelScope.launch {
        authDataSource.signOut()
    }

    fun register(email: String, password: String) = viewModelScope.launch {
        authDataSource.createUser(email, password).addOnCompleteListener { task ->
            with(task) {
                addOnSuccessListener {
                    _isRequestSuccess.value = true
                }
                addOnFailureListener {
                    _isRequestError.value = it.message
                }
            }
        }
    }
}