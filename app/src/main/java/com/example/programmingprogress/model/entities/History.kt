package com.example.programmingprogress.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp
import java.util.Calendar
import java.util.Date

@Parcelize
data class History(
    val date: Date = Date(),
    var description: String = "",
    var check: Boolean = false,
    var hours: Double = 0.0,
) : Parcelable
