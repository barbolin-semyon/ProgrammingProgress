package com.example.programmingprogress.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val countOfDaysSuccess: Int = 0,
    val countOfConsecutiveDaysSuccess: Int = 0,
    val hours: Int = 0,
  ) : Parcelable