package com.example.appfirebase.data.repository

import com.example.appfirebase.data.model.FirestoreModel
import com.example.appfirebase.data.model.User
import com.google.firebase.firestore.CollectionReference

interface FirestoreRepository<T: FirestoreModel> {
    val collection: CollectionReference

    suspend fun insert(doc: T): Boolean

    suspend fun getAll(): List<User>
}
