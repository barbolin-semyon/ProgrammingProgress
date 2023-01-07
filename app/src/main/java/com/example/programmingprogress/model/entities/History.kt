package com.example.programmingprogress.model.entities

import java.sql.Timestamp
import java.util.Date

data class History(
    val date: Date = Date(),
    val description: String = "",
    val isCheck: Boolean = false,
    val hours: Double = 0.0,
)
