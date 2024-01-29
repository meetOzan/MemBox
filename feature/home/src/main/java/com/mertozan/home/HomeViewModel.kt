package com.mertozan.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.usecase.DeleteAllMemoriesUseCase
import com.mertozan.membox.domain.usecase.DeleteLocalMemoriesUseCase
import com.mertozan.membox.domain.usecase.DeleteLocalUserUseCase
import com.mertozan.membox.domain.usecase.GetAllMemoriesUseCase
import com.mertozan.membox.domain.usecase.GetLocalMemoriesUseCase
import com.mertozan.membox.domain.usecase.GetLocalUserUserCase
import com.mertozan.membox.domain.usecase.GetUserNetworkUseCase
import com.mertozan.membox.domain.usecase.TransferToLocalUseCase
import com.mertozan.membox.domain.usecase.TransferUserUseCase
import com.mertozan.membox.model.Memory
import com.mertozan.membox.model.User
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
    private val deleteLocalMemoriesUseCase: DeleteLocalMemoriesUseCase,
    private val transferUserUseCase: TransferUserUseCase,
    private val getUserUseCase: GetUserNetworkUseCase,
    private val getLocalUserUseCase: GetLocalUserUserCase,
    private val deleteLocalUserUseCase: DeleteLocalUserUseCase
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
            HomeAction.GetLocalUser -> getLocalUser()
            HomeAction.GetUserNetwork -> getUserNetwork()
            is HomeAction.TransferUserToLocal -> transferUserToLocal(action.user)
            HomeAction.DeleteLocalUser -> deleteLocalUser()
        }
    }

    private fun transferMemoriesToLocal(memoryList: List<Memory>) {
        viewModelScope.launch {
            transferMemoriesToLocalUseCase(memoryList)
        }
    }

    private fun deleteLocalUser() {
        viewModelScope.launch {
            deleteLocalUserUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                        )
                    }
                }
            }
        }
    }

    private fun getALlMemories() {
        viewModelScope.launch {
            getAllMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            networkMemoryList = responseState.data
                        )
                    }
                }
            }
        }
    }

    private fun getUserNetwork() {
        viewModelScope.launch {
            getUserUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            networkUser = responseState.data
                        )
                    }
                }
            }
        }
    }

    private fun getLocalUser() {
        viewModelScope.launch {
            getLocalUserUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            isUserExist = responseState.data?.equals(User()) != true
                        )
                    }
                }
            }
        }
    }

    private fun transferUserToLocal(user: User) {
        viewModelScope.launch {
            transferUserUseCase(user).collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false
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
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                            memoryList = responseState.data
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
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                        )
                    }
                }
            }
        }
    }

    private fun deleteLocalMemories() {
        viewModelScope.launch {
            deleteLocalMemoriesUseCase().collect { responseState ->
                when (responseState) {
                    is ResponseState.Error -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = responseState.message
                        )
                        throw RuntimeException(_homeUiState.value.errorMessage)
                    }

                    ResponseState.Loading -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = true,
                            isError = false
                        )
                    }

                    is ResponseState.Success -> {
                        _homeUiState.value = _homeUiState.value.copy(
                            isLoading = false,
                            isError = false,
                        )
                    }
                }
            }
        }
    }

}

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val memoryList: List<Memory> = emptyList(),
    val networkMemoryList: List<Memory> = emptyList(),
    val isUserExist: Boolean = false,
    val networkUser: User = User(),
    val errorMessage: String = "",
) {
    companion object {
        fun initial() = HomeUiState(isLoading = true)
    }
}