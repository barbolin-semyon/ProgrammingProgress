package com.example.programmingprogress.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.parseCalendar(): Calendar {
    val date = this
    return Calendar.getInstance().apply { time = date }
}
fun Date.parseToShortString(): String = SimpleDateFormat("dd.MM.yyyy").format(this )
fun Calendar.getDate() = this.get(Calendar.DATE)
fun Calendar.getYear() = this.get(Calendar.YEAR)
fun Calendar.getMonth() = this.get(Calendar.MONTH)

fun Calendar.getDayOfWeek() = this.get(Calendar.DAY_OF_WEEK)
