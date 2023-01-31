package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.model.firebase.AuthDataSource
import com.example.programmingprogress.model.firebase.HistoryDataSource
import com.example.programmingprogress.util.*
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.util.*
import kotlin.math.max

class HistoryViewModel : ViewModel() {
    private val _history by lazy { MutableLiveData<List<History>>() }
    val history: MutableLiveData<List<History>>
        get() = _history

    private val _isNavigateBack = MutableLiveData<Boolean>(false)
    val isNavigateBack: LiveData<Boolean>
        get() = _isNavigateBack

    private val _isLoadTodayHistory by lazy { MutableLiveData<History>(null) }
    val isLoadTodayHistory: LiveData<History>
        get() = _isLoadTodayHistory

    private val _countConsecutiveDays = MutableLiveData(0)
    val countConsecutiveDays: LiveData<Int>
        get() = _countConsecutiveDays

    private val historyDataSource = HistoryDataSource
    private val authDataSource = AuthDataSource

    private lateinit var listener: ListenerRegistration

    fun enableListenerHistory(tempDate: Calendar) {

        val result = getDays(tempDate)
        val tempList = result.first
        val countDay = result.second

        val start = Timestamp(tempList[0].date.time)
        val end = Timestamp(tempList.last().date.time)

        listener =
            historyDataSource.getQueryHistory(authDataSource.getUserId()!!, start, end)
                .addSnapshotListener { value, error ->
                    value?.toObjects(History::class.java)?.forEach {
                        val date = it.date.parseCalendar()
                        val razn = if (tempDate.getYear() > date.getYear()) {
                            date.add(Calendar.DATE, 8)
                            tempDate.getDayOfYear() - date.getDayOfYear() + 8
                        } else {
                            tempDate.getDayOfYear() - date.getDayOfYear()
                        }

                        tempList[countDay - razn] = it
                    }
                    _history.value = tempList
                }
    }

    fun getCountConsecutiveDays() = viewModelScope.launch {
        historyDataSource.getAllHistory(authDataSource.getUserId()!!).addOnSuccessListener {
            var maxCount = 1
            val histories = it.toObjects(History::class.java)
            for (i in 1..histories.size) {
                if (histories[i].date.parseCalendar()
                        .getDate() - histories[i - 1].date.parseCalendar().getDate() == 1
                ) {
                    maxCount++
                } else {
                    maxCount = 1
                }
            }
            _countConsecutiveDays.value = maxCount
        }
    }

    fun disableListener() {
        listener.remove()
    }

    private fun getDays(currentDate: Calendar): Pair<MutableList<History>, Int> {
        currentDate.set(Calendar.DATE, 1)

        var countDayBeforeCurrentMonth = currentDate.getDayOfWeek() - 1
        if (countDayBeforeCurrentMonth == 0) countDayBeforeCurrentMonth = 7
        currentDate.add(Calendar.DATE, 1 - countDayBeforeCurrentMonth)

        val tempDays = mutableListOf<History>()
        val countDayInMonth =
            currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) + countDayBeforeCurrentMonth - 1

        repeat(countDayInMonth) {
            tempDays.add(History(currentDate.time))
            currentDate.add(Calendar.DATE, 1)
        }
        return Pair(tempDays, countDayInMonth)
    }

    fun getHistoryForToday() = viewModelScope.launch {
        withContext(this.coroutineContext) {
            val date = Calendar.getInstance().apply {
                set(Calendar.HOUR, 0)
                set(Calendar.MINUTE, 0)
            }

            val start = Timestamp(date.time.time)
            date.apply {
                set(Calendar.HOUR, 23)
                set(Calendar.MINUTE, 59)
            }
            val end = Timestamp(date.time.time)

            historyDataSource.getQueryHistory(authDataSource.getUserId()!!, start, end).get()
                .addOnSuccessListener {
                    var result = it.toObjects(History::class.java).getOrNull(0)

                    if (result == null) {
                        result = History(date = start)
                    }

                    _isLoadTodayHistory.value = result
                }
        }
    }

    fun updateHistory(history: History) = viewModelScope.launch {
        withContext(this.coroutineContext) {
            historyDataSource.updateHistoryElement(
                userId = authDataSource.getUserId()!!,
                history = history,
                onSuccess = { _isNavigateBack.value = true },
                onFailure = {}
            )
        }
    }

    /*fun getCountSuccessDays() {
        historyDataSource.getQueryCountSuccessHistory().addOnSuccessListener {
            _countSuccessDays.value = it.count
        }
    }*/
}