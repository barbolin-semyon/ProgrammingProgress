package com.example.programmingprogress.model.firebase

import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp
import java.util.Date

object HistoryDataSource {
    private val db = FirebaseFirestore.getInstance()

    fun getQueryReviews(startDate: Timestamp, endDate: Timestamp) {
        db.collection("users")
            .document("tHKEs9c73mNfXcAkX1Mk").collection("history")
            .orderBy("date")
            .startAt(startDate)
            .endBefore(endDate)
    }
}