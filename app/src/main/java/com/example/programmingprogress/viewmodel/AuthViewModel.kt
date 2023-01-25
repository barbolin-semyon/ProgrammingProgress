package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingprogress.model.firebase.AuthDataSource
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {
    private val authDataSource = AuthDataSource

    private val _isRequestSuccess = MutableLiveData(false)
    val isRequestSuccess: LiveData<Boolean>
        get() = _isRequestSuccess

    private val _isRequestError = MutableLiveData("")
    val isRequestError: LiveData<String>
        get() = _isRequestError

    fun isAuthorization() = authDataSource.getUserId() != null

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
