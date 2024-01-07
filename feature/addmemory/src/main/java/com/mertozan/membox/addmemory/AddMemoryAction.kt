package com.mertozan.membox.addmemory

import androidx.compose.ui.graphics.Color
import com.mertozan.membox.model.Memory

sealed class AddMemoryAction {
    data class AddMemory(val memory: Memory, val onNavigate: () -> Unit ) : AddMemoryAction()
    data class ChangeDescription(val newDescription: String) : AddMemoryAction()
    data class ChangeTitle(val newTitle: String) : AddMemoryAction()
    data class ChangeMood(val newMood: Int, val newEmojiName: String, val newEmojiColor: Color) : AddMemoryAction()
    data class ChangeDate(val newDate: String) : AddMemoryAction()
}