package com.example.programmingprogress.viewmodel

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
        currentDate.add(Calendar.DAY_OF_YEAR, - 7)
        enableListenerHistory()
    }

    fun enableListenerHistory() {
        val tempDate = currentDate.clone() as Calendar
        val start = Timestamp(tempDate.time.time)
        tempDate.add(Calendar.DAY_OF_YEAR, 7)
        val end = Timestamp(tempDate.time.time)

        val tempList = MutableList<History?>(7) { null }

        listener =
            historyDataSource.getQueryReviews(start, end).addSnapshotListener { value, error ->
                value?.toObjects(History::class.java)?.forEach {
                    tempList[it.date.date % 7] = it
                }
                fillTempList(tempList = tempList)
            }
    }

    fun fillTempList(tempList: MutableList<History?>) {
        tempList.forEachIndexed { index, history ->
            if (history == null) {
                val tempDateNow = currentDate.clone() as Calendar
                tempDateNow.add(Calendar.DAY_OF_YEAR, index)
                tempList[index] = History(date = tempDateNow.time)
            }
        }

            _history.value = tempList.toList()
    }

    fun disableListener() {
        listener.remove()
    }
}