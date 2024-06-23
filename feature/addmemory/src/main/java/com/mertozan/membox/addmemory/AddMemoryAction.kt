package com.mertozan.membox.addmemory

import android.content.Context
import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.mertozan.membox.model.Memory

sealed class AddMemoryAction {
    data class AddMemory(val memory: Memory, val onNavigate: () -> Unit) : AddMemoryAction()
    data class AddMemoryToLocal(val memory: Memory) : AddMemoryAction()
    data class UploadImageStorage(
        val uri: Uri,
        val context: Context,
        val onSuccess: (String, String) -> Unit,
        val onFailure: (String) -> Unit,
    ) : AddMemoryAction()
    data class ChangeDescription(val newDescription: String) : AddMemoryAction()
    data class ChangeTitle(val newTitle: String) : AddMemoryAction()
    data class ChangeMood(val newMood: Int, val newEmojiName: String, val newEmojiColor: Color) :
        AddMemoryAction()
    data class ChangeDate(val newDate: String) : AddMemoryAction()
    data class SetImageUri(val uri: Uri, val url: String) : AddMemoryAction()
}