package com.example.programmingprogress.model.empties

import java.util.Date

data class History(
    val date: Date,
    val description: String,
    val isCheck: Boolean,
    val hours: Double,
)
