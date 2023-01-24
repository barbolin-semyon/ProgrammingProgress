package com.example.programmingprogress.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class User(
    val id: String = "",
    val name: String = "",
    val score: Int = 0,
    val countOfDaysSuccess: Int = 0,
    val countOfConsecutiveDaysSuccess: Int = 0,
    val lastDateOfConsecutiveDays: Date = Date(),
  ) : Parcelable