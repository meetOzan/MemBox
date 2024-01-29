package com.mertozan.membox.source.network

import android.content.Context
import android.net.Uri
import com.mertozan.membox.model.Memory
import com.mertozan.membox.source.network.dto.User

interface FirebaseSource {

    // User
    fun signUpUserWithEmailAndPassword(user: User, onNavigate: () -> Unit)

    fun signInWithEmailAndPassword(user: User, onNavigate: () -> Unit)

    fun signOut()

    fun isUserSignedIn(): Boolean

    suspend fun getUserNetwork(): User

    // Memory
    fun addMemory(memory: Memory, onNavigate: () -> Unit)

    fun getAllMemories(): List<Memory>

    fun getMemoryByTitle(title: String): Memory

    fun getMemoryByDate(date: String): List<Memory>

    fun uploadImageToStorage(
        uri: Uri,
        context: Context,
        onSuccess: (String, String) -> Unit = { _, _ -> },
        onFailure: (String) -> Unit,
    )

    fun uploadImageToFirestore(
        imagesUrl: List<String>,
        imageName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    )

    fun getMoodsFromMemories(): Map<String, Float>

    fun deleteAllMemories()
}