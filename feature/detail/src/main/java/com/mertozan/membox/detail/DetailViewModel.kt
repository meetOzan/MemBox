package com.mertozan.membox.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.core.consts.Constants.ARGS_NAME
import com.mertozan.membox.domain.usecase.GetMemoryByNameUseCase
import com.mertozan.membox.domain.usecase.GetSingleMemoryUseCase
import com.mertozan.membox.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSingleMemoryUseCase: GetSingleMemoryUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _detailUiState = MutableStateFlow(DetailUiState.initial)
    val detailUiState = _detailUiState.asStateFlow()

    private val detailTitle = savedStateHandle.get<String>(key = ARGS_NAME)

    fun onAction(detailAction: DetailAction) {
        when (detailAction) {
            is DetailAction.ChangeMemoryTitle -> changeMemoryTitle(detailAction.memoryName)
            is DetailAction.ChangeMemoryContent -> changeMemoryContent(detailAction.memoryContent)
            is DetailAction.GetMemoryByName -> getDetail(detailTitle.orEmpty())
        }
    }

    private fun getDetail(title: String) {
        viewModelScope.launch {
            getSingleMemoryUseCase(title).collect { responseState ->
                when (responseState) {
                    is ResponseState.Loading -> {
                        _detailUiState.value = _detailUiState.value.copy(isLoading = true)
                    }

                    is ResponseState.Error -> {
                        _detailUiState.value = _detailUiState.value.copy(
                            isErrorAction = true,
                            errorMessage = responseState.message
                        )
                    }

                    is ResponseState.Success -> {
                        _detailUiState.value =
                            _detailUiState.value.copy(memory = responseState.data)
                    }
                }
            }
        }
    }

    private fun changeMemoryTitle(memoryName: String) {
        _detailUiState.value = _detailUiState.value.copy(memoryName = memoryName)
    }

    private fun changeMemoryContent(memoryContent: String) {
        _detailUiState.value = _detailUiState.value.copy(memoryContent = memoryContent)
    }

}

data class DetailUiState(
    val isLoading: Boolean = false,
    val isErrorAction: Boolean = false,
    val errorMessage: String = "",
    val memory: Memory = Memory(),
    val memoryName: String = "",
    val memoryContent: String = "",
) {
    companion object {
        val initial = DetailUiState(
            isLoading = true
        )
    }
}
