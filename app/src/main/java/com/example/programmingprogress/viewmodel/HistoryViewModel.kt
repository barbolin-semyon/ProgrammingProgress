package com.example.programmingprogress.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingprogress.model.entities.History
import com.example.programmingprogress.model.firebase.HistoryDataSource
import com.example.programmingprogress.util.getDate
import com.example.programmingprogress.util.getDayOfWeek
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

    private val _isNavigateBack = MutableLiveData<Boolean>(false)
    val isNavigateBack: LiveData<Boolean>
        get() = _isNavigateBack

    private val historyDataSource = HistoryDataSource
    private lateinit var listener: ListenerRegistration

    init {
        enableListenerHistory(Calendar.getInstance())
    }
    fun enableListenerHistory(tempDate: Calendar) {

        val result = getDays(tempDate)
        val tempList = result.first
        val countDay = result.second

        val start = Timestamp(tempList[0].date.time)
        val end = Timestamp(tempList.last().date.time)

        listener =
            historyDataSource.getQueryReviews(start, end).addSnapshotListener { value, error ->
                value?.toObjects(History::class.java)?.forEach {
                    val date = it.date.parseCalendar()

                    val razn =
                        (tempDate.get((Calendar.DAY_OF_YEAR))) - date.get(Calendar.DAY_OF_YEAR)
                    tempDate.get((Calendar.DAY_OF_YEAR))
                    date.get(Calendar.DAY_OF_YEAR)
                    tempList[countDay - razn] = it
                }
                _history.value = tempList
            }
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

            historyDataSource.getQueryReviews(start, end).get().addOnSuccessListener {
                _isLoadTodayHistory.value = it.toObjects(History::class.java).getOrNull(0)
            }
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