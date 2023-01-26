package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingprogress.model.entities.User
import com.example.programmingprogress.model.firebase.AuthDataSource
import com.example.programmingprogress.util.AuthorizationType
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {
    private val authDataSource = AuthDataSource

    private val _isRequestError = MutableLiveData("")
    val isRequestError: LiveData<String>
        get() = _isRequestError

    private val _typeAuthorization = MutableLiveData(AuthorizationType.LOADING)
    val typeAuthorization: LiveData<AuthorizationType>
        get() = _typeAuthorization

    fun checkAuthorization() = viewModelScope.launch {
        val result = async { authDataSource.getUserId() != null }.await()
        _typeAuthorization.value =
            if (result) AuthorizationType.AUTHORIZATION else AuthorizationType.NOT_AUTHORIZATION
    }

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            authDataSource.signInWithEmail(email, password).addOnCompleteListener { task ->
                with(task) {
                    addOnSuccessListener {
                        _typeAuthorization.value = AuthorizationType.AUTHORIZATION
                    }
                    addOnFailureListener {
                        _isRequestError.value = it.message
                    }
                }
            }
        }
    }

    fun signOut() = viewModelScope.launch {
        async { authDataSource.signOut() }.await()
        _typeAuthorization.value = AuthorizationType.NOT_AUTHORIZATION
    }

    fun register(email: String, nickname: String, password: String) = viewModelScope.launch {
        authDataSource.createUser(email, password).addOnCompleteListener { task ->
            with(task) {
                addOnSuccessListener {
                    launch {
                        val user = User(
                            email = email,
                            id = it.user!!.uid,
                            name = nickname
                        )

                        authDataSource.addUserInDb(user)
                            .addOnSuccessListener {
                                _typeAuthorization.value = AuthorizationType.AUTHORIZATION
                            }
                    }
                }
                addOnFailureListener {
                    _isRequestError.value = it.message
                }
            }
        }
    }
}
