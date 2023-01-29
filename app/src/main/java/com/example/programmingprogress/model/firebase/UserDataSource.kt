package com.example.programmingprogress.model.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object UserDataSource {
    val db = FirebaseFirestore.getInstance()
    val ioDispatcher = Dispatchers.IO

    suspend fun getInformationUser(userId: String) = withContext(ioDispatcher) {
        return@withContext db.collection("users").document(userId).get()
    }

    suspend fun updateCountOfDaysAndHourSuccess(
        userId: String,
        countOfDaysSuccess: Int,
        hours: Double
    ) =
        withContext(ioDispatcher) {
            return@withContext db.collection("users").document(userId)
                .update(
                    "countOfDaysSuccess", countOfDaysSuccess,
                    "hours", hours
                )
        }

    suspend fun updateCountOfConsecutiveDays(
        userId: String,
        countOfConsecutiveDays: Int,
    ) = withContext(ioDispatcher) {
        return@withContext db.collection("users").document(userId)
            .update(
                "countOfConsecutiveDaysSuccess", countOfConsecutiveDays,
            )
    }
}