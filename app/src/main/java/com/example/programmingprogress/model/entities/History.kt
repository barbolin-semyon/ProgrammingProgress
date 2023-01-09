package com.example.programmingprogress.model.entities

import java.util.Calendar
import java.util.Date

data class History(
    private val date: Date = Date(),
    val description: String = "",
    val check: Boolean = false,
    val hours: Double = 0.0,
) {
    fun getDate(): Calendar {
        return Calendar.getInstance().apply {
            time = date
        }
    }
}
