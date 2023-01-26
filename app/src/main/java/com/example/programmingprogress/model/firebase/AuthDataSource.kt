package com.example.programmingprogress.model.firebase

import com.example.programmingprogress.model.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AuthDataSource {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val ioDispatcher = Dispatchers.IO
    suspend fun getUserId() = auth.currentUser?.uid

    suspend fun signOut() = withContext(ioDispatcher) {
        auth.signOut()
    }

    suspend fun signInWithEmail(email: String, password: String) = withContext(ioDispatcher) {
        return@withContext auth.signInWithEmailAndPassword(email, password)
    }

    suspend fun createUser(email: String, password: String) = withContext(ioDispatcher) {
        return@withContext auth.createUserWithEmailAndPassword(email, password)
    }

    suspend fun addUserInDb(user: User) = withContext(ioDispatcher) {
        return@withContext db.collection("users").document(user.id).set(user)
    }
}