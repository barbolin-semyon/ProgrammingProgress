package com.example.programmingprogress.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Calendar
import java.util.Date

@Parcelize
data class History(
    private val date: Date = Date(),
    var description: String = "",
    val check: Boolean = false,
    var hours: Double = 0.0,
) : Parcelable {
    fun getDate(): Calendar {
        return Calendar.getInstance().apply {
            time = date
        }
    }
}
