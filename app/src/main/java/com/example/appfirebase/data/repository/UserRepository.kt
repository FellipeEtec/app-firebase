package com.example.appfirebase.data.repository

import com.example.appfirebase.data.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepository(
    db: FirebaseFirestore = Firebase.firestore
) : FirestoreRepository<User> {
    override val collection = db.collection("users")

    override suspend fun insert(doc: User): Boolean {
        if (!doc.verifySavable())
            return false

        return try {
            collection.add(doc).await()
            true
        } catch (_: Exception) {
            false
        }
    }

    override suspend fun getAll(): List<User> {
        return suspendCoroutine { continuation ->
            collection
                .get()
                .addOnSuccessListener { result ->
                    val users = result.documents.mapNotNull { document ->
                        document.toObject(User::class.java)
                    }
                    continuation.resume(users)
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }
}
