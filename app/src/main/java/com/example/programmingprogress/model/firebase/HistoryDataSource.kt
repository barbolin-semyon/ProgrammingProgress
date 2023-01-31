package com.example.programmingprogress.model.firebase

import com.example.programmingprogress.model.entities.History
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Timestamp

object HistoryDataSource {
    private val db = FirebaseFirestore.getInstance()

    fun updateHistoryElement(
        userId: String,
        history: History,
        onSuccess: () -> Unit,
        onFailure: (message: Exception) -> Unit
    ) {
        db.collection("users")
            .document(userId)
            .collection("history").add(history)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun getQueryHistory(userId: String, startDate: Timestamp, endDate: Timestamp): Query {
        return db.collection("users")
            .document(userId).collection("history")
            .orderBy("date")
            .startAt(startDate)
            .endBefore(endDate)
    }

    suspend fun getAllHistory(userId: String) = withContext(Dispatchers.IO) {
        return@withContext db.collection("users")
            .document(userId).collection("history")
            .orderBy("date").get()
    }

    fun getQueryCountSuccessHistory(userId: String,): Task<AggregateQuerySnapshot> {
        return db.collection("users")
            .document(userId)
            .collection("history").whereEqualTo("check", true)
            .count().get(AggregateSource.SERVER)
    }
}