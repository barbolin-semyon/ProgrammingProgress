package com.example.programmingprogress.viewmodel

import androidx.compose.animation.scaleIn
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.model.firebase.HistoryDataSource
import com.google.firebase.firestore.ListenerRegistration
import java.sql.Timestamp
import java.util.*

class HistoryViewModel : ViewModel() {
    private val _history by lazy { MutableLiveData<List<History?>>() }
    val history: MutableLiveData<List<History?>>
        get() = _history

    private val historyDataSource = HistoryDataSource
    private lateinit var listener: ListenerRegistration

    fun enableListenerHistory(currentTask: Calendar) {



        val start = Timestamp(currentTask.time.time)
        currentTask.set(Calendar.DAY_OF_YEAR, currentTask.time.date + 7)
        val end = Timestamp(currentTask.time.time)

        val tempList = MutableList<History?>(7) { null }

        listener =
            historyDataSource.getQueryReviews(start, end).addSnapshotListener { value, error ->
                value?.toObjects(History::class.java)?.forEach {
                    tempList[it.date.date % 7] = it
                }

                _history.value = tempList.toList()
            }
    }

    fun disableListener() {
        listener.remove()
    }
}