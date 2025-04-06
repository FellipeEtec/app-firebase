package com.example.appfirebase.data.repository

import com.example.appfirebase.data.model.FirestoreModel
import com.google.firebase.firestore.CollectionReference

interface Repository<T: FirestoreModel> {
    val collection: CollectionReference

    suspend fun insert(doc: T): Boolean
}
