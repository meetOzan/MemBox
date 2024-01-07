package com.mertozan.membox.addmemory

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.AddMemoryUseCase
import com.mertozan.membox.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoryViewModel @Inject constructor(
    private val addMemoryUseCase: AddMemoryUseCase,
) : ViewModel() {

    private val _addMemoryState = MutableStateFlow(AddMemoryState.initial())
    val addMemoryState = _addMemoryState.asStateFlow()

    fun onAction(action: AddMemoryAction) {
        when (action) {
            is AddMemoryAction.AddMemory -> addMemory(action.memory, action.onNavigate)
            is AddMemoryAction.ChangeDescription -> changeDescription(action.newDescription)
            is AddMemoryAction.ChangeTitle -> changeTitle(action.newTitle)
            is AddMemoryAction.ChangeMood -> changeMood(action.newMood, action.newEmojiName, action.newEmojiColor)
            is AddMemoryAction.ChangeDate -> changeDate(action.newDate)
        }
    }

    private fun addMemory(memory: Memory, onNavigate: () -> Unit) {
        viewModelScope.launch {
            addMemoryUseCase(memory, onNavigate).collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _addMemoryState.value = _addMemoryState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_addMemoryState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _addMemoryState.value = _addMemoryState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResponseState.Success -> {
                        _addMemoryState.value = _addMemoryState.value.copy(
                            isLoading = false,
                            isSuccess = true,
                        )
                    }
                }

            }
        }
    }

    private fun changeDescription(newDescription: String) {
        _addMemoryState.value = _addMemoryState.value.copy(
            description = newDescription
        )
    }

    private fun changeTitle(newTitle: String) {
        _addMemoryState.value = _addMemoryState.value.copy(
            title = newTitle
        )
    }

    private fun changeMood(newMood: Int, newEmojiName: String, newEmojiColor: Color) {
        _addMemoryState.value = _addMemoryState.value.copy(
            mood = newMood,
            emojiName = newEmojiName,
            emojiColor = newEmojiColor
        )
    }

    private fun changeDate(newDate: String) {
        _addMemoryState.value = _addMemoryState.value.copy(
            date = newDate
        )
    }
}

data class AddMemoryState(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val image: List<String> = emptyList(),
    val mood: Int = 0,
    val emojiName: String = "",
    val emojiColor: Color = Color.LightGray,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
) {
    companion object {
        fun initial() = AddMemoryState(isLoading = true)
    }
}