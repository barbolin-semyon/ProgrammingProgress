package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.model.firebase.HistoryDataSource
import com.example.programmingprogress.util.parseCalendar
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.util.*

class HistoryViewModel : ViewModel() {
    private val _history by lazy { MutableLiveData<List<History>>() }
    val history: MutableLiveData<List<History>>
        get() = _history

    private var currentDate = Calendar.getInstance()

    private val _isNavigateBack = MutableLiveData<Boolean>(false)
    val isNavigateBack: LiveData<Boolean>
        get() = _isNavigateBack

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

    fun setCurrentDate(date: Calendar) {
        currentDate = date
        enableListenerHistory()
    }

    fun enableListenerHistory() {
        val tempDate = currentDate.clone() as Calendar
        val start = Timestamp(tempDate.time.time)

        val tempList = mutableListOf<History>()
        repeat(7) {
            tempList.add(History(date = tempDate.time))
            tempDate.add(Calendar.DAY_OF_YEAR, 1)
        }

        val end = Timestamp(tempDate.time.time)

        listener =
            historyDataSource.getQueryReviews(start, end).addSnapshotListener { value, error ->
                value?.toObjects(History::class.java)?.forEach {
                    val razn = (tempDate.get((Calendar.DAY_OF_YEAR))) - it.date.parseCalendar()
                        .get(Calendar.DAY_OF_YEAR)
                    tempDate.get((Calendar.DAY_OF_YEAR))
                    it.date.parseCalendar().get(Calendar.DAY_OF_YEAR)
                    tempList[7 - razn] = it
                }
                _history.value = tempList
            }


    }

    fun updateHistory(history: History) = viewModelScope.launch {
        withContext(this.coroutineContext) {
            historyDataSource.updateHistoryElement(
                history = history,
                onSuccess = { _isNavigateBack.value = true },
                onFailure = {}
            )
        }
    }

    fun disableListener() {
        listener.remove()
    }
}