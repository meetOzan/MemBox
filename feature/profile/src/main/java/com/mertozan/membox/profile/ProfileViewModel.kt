package com.mertozan.membox.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.DeleteAllMemoriesUseCase
import com.mertozan.membox.domain.DeleteLocalMemoriesUseCase
import com.mertozan.membox.domain.GetAllMemoriesUseCase
import com.mertozan.membox.domain.GetLocalMemoriesUseCase
import com.mertozan.membox.domain.GetLocalMoodsUseCase
import com.mertozan.membox.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAllMemoriesUseCase: GetAllMemoriesUseCase,
    private val deleteLocalMemoriesUseCase: DeleteLocalMemoriesUseCase,
    private val deleteAllMemoriesUseCase: DeleteAllMemoriesUseCase,
    private val getLocalMemoriesUseCase: GetLocalMemoriesUseCase,
    private val getLocalMoodsUseCase: GetLocalMoodsUseCase,
) : ViewModel() {

    private val _profileUiState = MutableStateFlow(ProfileUiState.initial())
    val profileUiState = _profileUiState.asStateFlow()

    fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.GetAllMemories -> getAllMemories()
            is ProfileAction.GetMoods -> getMoods()
            is ProfileAction.DeleteAllMemoriesNetwork -> deleteAllMemories()
            is ProfileAction.DeleteAllMemoriesFromLocal -> deleteAllMemoriesFromLocal()
            is ProfileAction.ChangeDialogState -> changeDialogState()
            is ProfileAction.GetLocalMemories -> getLocalMemories()
        }
    }

    private fun getAllMemories() {
        viewModelScope.launch {
            getAllMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_profileUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                            profileMemoryStreak = responseState.data.forEach {
                                var streak = 0
                                if (it.date.toInt() == it.date.toInt() - 1) {
                                    streak++
                                }
                                streak.toString()
                            }.toString(),
                            memoryList = responseState.data
                        )
                    }
                }
            }
        }
    }

    private fun getMoods() {
        viewModelScope.launch {
            getLocalMoodsUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_profileUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                            moodValueMap = responseState.data,
                        )
                    }
                }
            }
        }
    }

    private fun deleteAllMemories() {
        viewModelScope.launch {
            deleteAllMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_profileUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                        )
                    }
                }
            }
        }
    }

    private fun deleteAllMemoriesFromLocal() {
        viewModelScope.launch {
            deleteLocalMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_profileUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                        )
                    }
                }
            }
        }
    }

    private fun getLocalMemories() {
        viewModelScope.launch {
            getLocalMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_profileUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _profileUiState.value = _profileUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                            memoryList = responseState.data
                        )
                    }
                }
            }
        }
    }

    private fun changeDialogState() {
        _profileUiState.value = _profileUiState.value.copy(
            isDialogOpen = !_profileUiState.value.isDialogOpen
        )
    }

}

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val profileName: String = "",
    val profileMemoryStreak: String = "",
    val moodValueMap: Map<String, Float> = mapOf(
        "Happy" to 0.1f,
        "Sad" to 0.1f,
        "Angry" to 0.1f,
        "Love" to 0.1f,
        "AngryCry" to 0.1f,
    ),
    val memoryList: List<Memory> = listOf(),
    val isDialogOpen: Boolean = false,
) {
    companion object {
        fun initial() = ProfileUiState(isLoading = true)
    }
}