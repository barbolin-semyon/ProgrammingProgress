package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.programmingprogress.model.firebase.HistoryDataSource

class UserViewModel() : ViewModel() {
    private val dataSource = HistoryDataSource


}