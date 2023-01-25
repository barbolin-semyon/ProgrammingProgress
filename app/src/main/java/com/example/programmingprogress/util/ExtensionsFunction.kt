package com.example.programmingprogress.util

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

fun Date.parseCalendar(): Calendar {
    val date = this
    return Calendar.getInstance().apply { time = date }
}

fun Date.parseToShortString(): String = SimpleDateFormat("dd.MM.yyyy").format(this)
fun Calendar.getDate() = this.get(Calendar.DATE)
fun Calendar.getYear() = this.get(Calendar.YEAR)
fun Calendar.getDayOfYear() = this.get(Calendar.DAY_OF_YEAR)
fun Calendar.getMonth() = this.get(Calendar.MONTH)
fun Calendar.getDayOfWeek() = this.get(Calendar.DAY_OF_WEEK)

fun String.checkValidEmail() =
    android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()


