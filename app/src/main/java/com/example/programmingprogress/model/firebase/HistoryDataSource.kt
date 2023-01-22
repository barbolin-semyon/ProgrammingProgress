package com.example.programmingprogress.model.firebase

import com.example.programmingprogress.model.entities.History
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.sql.Timestamp
import java.util.Date

object HistoryDataSource {
    private val db = FirebaseFirestore.getInstance()

    fun updateHistoryElement(
        history: History,
        onSuccess: () -> Unit,
        onFailure: (message: Exception) -> Unit
    ) {
        db.collection("users")
            .document("tHKEs9c73mNfXcAkX1Mk")
            .collection("history").add(history)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun getQueryReviews(startDate: Timestamp, endDate: Timestamp): Query {
        return db.collection("users")
            .document("tHKEs9c73mNfXcAkX1Mk").collection("history")
            .orderBy("date")
            .startAt(startDate)
            .endBefore(endDate)
    }
}