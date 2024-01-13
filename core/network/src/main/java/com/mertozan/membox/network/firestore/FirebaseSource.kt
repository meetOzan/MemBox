package com.mertozan.membox.network.firestore

import android.content.Context
import android.net.Uri
import com.mertozan.membox.model.Memory
import com.mertozan.membox.network.dto.User

interface FirebaseSource {

    fun signUpUserWithEmailAndPassword(user: User, onNavigate: () -> Unit)

    fun signInWithEmailAndPassword(user: User, onNavigate: () -> Unit)

    fun signOut()

    fun isUserSignedIn(): Boolean

    fun addMemory(memory: Memory, onNavigate: () -> Unit)

    fun getAllMemories(): List<Memory>

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
        onFailure: (String) -> Unit
    )

}