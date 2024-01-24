package com.mertozan.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.DeleteAllMemoriesUseCase
import com.mertozan.membox.domain.DeleteLocalMemoriesUseCase
import com.mertozan.membox.domain.GetAllMemoriesUseCase
import com.mertozan.membox.domain.GetLocalMemoriesUseCase
import com.mertozan.membox.domain.TransferToLocalUseCase
import com.mertozan.membox.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllMemoriesUseCase: GetAllMemoriesUseCase,
    private val getLocalMemoriesUseCase: GetLocalMemoriesUseCase,
    private val transferMemoriesToLocalUseCase: TransferToLocalUseCase,
    private val deleteAllMemoriesUseCase: DeleteAllMemoriesUseCase,
    private val deleteLocalMemoriesUseCase: DeleteLocalMemoriesUseCase
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState.initial())
    val homeUiState = _homeUiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.GetAllMemories -> getALlMemories()
            is HomeAction.GetLocalMemories -> getLocalMemories()
            is HomeAction.TransferMemoriesToLocal -> transferMemoriesToLocal(action.memoryList)
            HomeAction.DeleteAllMemories -> deleteAllMemories()
            HomeAction.DeleteLocalMemories -> deleteLocalMemories()
        }
    }

    private fun transferMemoriesToLocal(memoryList: List<Memory>) {
        viewModelScope.launch {
            transferMemoriesToLocalUseCase(memoryList)
        }
    }


    private fun getALlMemories() {
        viewModelScope.launch {
            getAllMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                            networkMemoryList = responseState.data
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
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
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

    private fun deleteAllMemories(){
        viewModelScope.launch {
            deleteAllMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                        )
                    }
                }
            }
        }
    }

    private fun deleteLocalMemories(){
        viewModelScope.launch {
            deleteLocalMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isSuccess = false,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isSuccess = true,
                        )
                    }
                }
            }
        }
    }

}

data class HomeUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val memoryList: List<Memory> = emptyList(),
    val networkMemoryList : List<Memory> = emptyList(),
    val errorMessage: String = "",
) {
    companion object {
        fun initial() = HomeUiState(isLoading = true)
    }
}