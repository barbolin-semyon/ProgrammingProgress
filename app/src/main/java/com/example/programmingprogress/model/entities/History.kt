package com.example.programmingprogress.model.entities

import java.util.Date

data class History(
    val date: Date = Date(),
    val description: String = "",
    val check: Boolean = false,
    val hours: Double = 0.0,
)
