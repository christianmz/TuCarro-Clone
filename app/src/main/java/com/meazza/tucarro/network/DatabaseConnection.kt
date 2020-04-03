package com.meazza.tucarro.network

import com.google.firebase.database.FirebaseDatabase
import com.meazza.tucarro.model.User
import kotlinx.coroutines.tasks.await

object DatabaseConnection {

    private const val PATH_USERS = "users"

    private val mDatabase: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }

    suspend fun addUser(user: User) {
        mDatabase.getReference(PATH_USERS).push().setValue(user).await()
    }
}