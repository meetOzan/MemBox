package com.mertozan.membox.repository

import android.content.Context
import android.net.Uri
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.model.Memory
import kotlinx.coroutines.flow.Flow

interface MemRepository {

    // Firestore - Network
    fun addMemory(memory: Memory, onNavigate: () -> Unit): Flow<ResponseState<Unit>>

    fun getAllMemories(): Flow<ResponseState<List<Memory>>>

    fun getMemoryByDate(date: String): Flow<ResponseState<List<Memory>>>

    // Image
    fun uploadImageStorage(
        uri: Uri,
        context: Context,
        onSuccess: (String, String) -> Unit = { _, _ -> },
        onFailure: (String) -> Unit = { _ -> },
    ): Flow<ResponseState<Unit>>

    fun uploadImageToFirestore(
        imagesUrl: List<String>,
        imageName: String,
        onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit = { _ -> },
    ): Flow<ResponseState<Unit>>

    fun getMoodsFromMemories(): Flow<ResponseState<Map<String, Float>>>

    fun deleteAllMemories(): Flow<ResponseState<Unit>>

    // Locale
    fun deleteMemoryFromLocale(memory: Memory): Flow<ResponseState<Unit>>

    fun updateMemory(memory: Memory): Flow<ResponseState<Unit>>

    fun getMemoryCount(): Flow<ResponseState<Int>>

    fun getAllLocalMemories(): Flow<ResponseState<List<Memory>>>

    fun addMemoryToLocal(memory: Memory): Flow<ResponseState<Unit>>

    // Transfer
    fun transferMemoriesToLocale(): Flow<ResponseState<Unit>>

}