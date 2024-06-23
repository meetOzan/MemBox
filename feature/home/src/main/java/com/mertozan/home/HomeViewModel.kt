package com.mertozan.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
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
            is HomeAction.ChangeNotificationStatus -> changeNotificationStatus(action.newStatus)
            HomeAction.GetCurrentDate -> getCurrentDate()
            is HomeAction.ChangePopupMenuVisibility -> changePopupMenuVisibility(action.popUpVisibility)
            is HomeAction.ChangePopupMenuOffset -> changePopupMenuVisibility(action.offset)
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

    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return currentDate.format(formatter).toString()
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

    private fun changeNotificationStatus(newStatus: Boolean) {
        viewModelScope.launch {
            _homeUiState.value = _homeUiState.value.copy(
                isMemoryAdded = newStatus
            )
        }
    }

    private fun changePopupMenuVisibility(newStatus: Boolean) {
        viewModelScope.launch {
            _homeUiState.value = _homeUiState.value.copy(
                isPopupMenuVisible = newStatus
            )
        }
    }

    private fun changePopupMenuVisibility(offset: DpOffset) {
        viewModelScope.launch {
            _homeUiState.value = _homeUiState.value.copy(
                buttonOffset = offset
            )
        }
    }

}


data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val memoryList: List<Memory> = emptyList(),
    val networkMemoryList: List<Memory> = emptyList(),
    val isUserExist: Boolean = false,
    val isMemoryAdded: Boolean = true,
    val networkUser: User = User(),
    val errorMessage: String = "",
    val isPopupMenuVisible: Boolean = false,
    val buttonOffset: DpOffset = DpOffset(0.dp, 0.dp)
) {
    companion object {
        fun initial() = HomeUiState(isLoading = true)
    }
}