package com.example.programmingprogress.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.model.firebase.HistoryDataSource
import com.google.firebase.firestore.ListenerRegistration
import java.sql.Timestamp
import java.util.*

class HistoryViewModel : ViewModel() {
    private val _history by lazy { MutableLiveData<List<History>>() }
    val history: MutableLiveData<List<History>>
        get() = _history

    private val currentDate by lazy { Calendar.getInstance() }

    private val historyDataSource = HistoryDataSource
    private lateinit var listener: ListenerRegistration

    fun increaseCurrentDate() {
        disableListener()
        currentDate.add(Calendar.DAY_OF_YEAR, 7)
        enableListenerHistory()
    }

    fun decreaseCurrentDate() {
        disableListener()
        currentDate.add(Calendar.DAY_OF_YEAR, -7)
        enableListenerHistory()
    }

    fun enableListenerHistory() {
        val tempDate = currentDate.clone() as Calendar
        val start = Timestamp(tempDate.time.time)

        val tempList = mutableListOf<History>()
        repeat(7) {
            tempDate.add(Calendar.DAY_OF_YEAR, 1)
            tempList.add(History(date = tempDate.time))
        }

        val end = Timestamp(tempDate.time.time)

        listener =
            historyDataSource.getQueryReviews(start, end).addSnapshotListener { value, error ->
                value?.toObjects(History::class.java)?.forEach {
                    val razn = (tempDate.get((Calendar.DAY_OF_YEAR))) - it.getDate().get(Calendar.DAY_OF_YEAR)
                    tempList[6 - razn] = it
                }
                _history.value = tempList
            }


    }

    fun disableListener() {
        listener.remove()
    }
}