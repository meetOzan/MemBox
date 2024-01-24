package com.mertozan.membox.addmemory

import android.content.Context
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.AddMemoryUseCase
import com.mertozan.membox.domain.AddToLocalUseCase
import com.mertozan.membox.domain.UploadImageFirestoreUseCase
import com.mertozan.membox.domain.UploadImageStorageUseCase
import com.mertozan.membox.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoryViewModel @Inject constructor(
    private val addMemoryUseCase: AddMemoryUseCase,
    private val uploadImageStorageUseCase: UploadImageStorageUseCase,
    private val uploadImageFirestoreUseCase: UploadImageFirestoreUseCase,
    private val addMemoryToLocalUseCase: AddToLocalUseCase
) : ViewModel() {

    private val _addMemoryState = MutableStateFlow(AddMemoryState.initial())
    val addMemoryState = _addMemoryState.asStateFlow()

    fun onAction(action: AddMemoryAction) {
        when (action) {
            is AddMemoryAction.AddMemory -> addMemory(action.memory, action.onNavigate)
            is AddMemoryAction.AddMemoryToLocal -> addMemoryToLocal(action.memory)
            is AddMemoryAction.UploadImageFirestore -> uploadImageToFirestore(
                imagesUrl = action.imagesUrl,
                imageName = action.imageName,
                onSuccess = action.onSuccess,
                onFailure = action.onFailure,
            )

            is AddMemoryAction.ChangeDescription -> changeDescription(action.newDescription)
            is AddMemoryAction.ChangeTitle -> changeTitle(action.newTitle)
            is AddMemoryAction.ChangeMood -> changeMood(
                action.newMood,
                action.newEmojiName,
                action.newEmojiColor
            )

            is AddMemoryAction.ChangeDate -> changeDate(action.newDate)
            is AddMemoryAction.UploadImageStorage -> uploadImage(
                uri = action.uri,
                context = action.context,
                onSuccess = action.onSuccess,
                onFailure = action.onFailure,
            )

            is AddMemoryAction.SetImageUri -> setImageUri(action.uri, action.url)
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

    private fun uploadImage(
        uri: Uri,
        context: Context,
        onSuccess: (String, String) -> Unit = { _, _ -> },
        onFailure: (String) -> Unit = { _ -> },
    ) {
        viewModelScope.launch {
            uploadImageStorageUseCase(uri, context, onSuccess, onFailure).collect { responseState ->
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
                        )
                    }
                }

            }
        }
    }

    private fun uploadImageToFirestore(
        imagesUrl: List<String>,
        imageName: String,
        onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit = { _ -> },
    ) {
        viewModelScope.launch {
            uploadImageFirestoreUseCase(
                imagesUrl,
                imageName,
                onSuccess,
                onFailure
            ).collect { responseState ->
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
                            isLoading = false
                        )
                    }
                }

            }
        }
    }

    private fun addMemoryToLocal(memory: Memory) {
        viewModelScope.launch {
            addMemoryToLocalUseCase(memory).collect { responseState ->
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
                            isLoading = false

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

    private fun setImageUri(uri: Uri, url: String) {
        with(_addMemoryState.value) {
            _addMemoryState.value = this.copy(
                images = images.apply { set(photoNumber, uri) },
                imagesUrl = imagesUrl.apply { set(photoNumber, url) },
                imageUri = uri,
                photoNumber = photoNumber + 1
            )
        }
    }
}

data class AddMemoryState(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val images: MutableList<Uri> = mutableListOf(Uri.EMPTY, Uri.EMPTY, Uri.EMPTY, Uri.EMPTY),
    val imagesUrl: MutableList<String> = mutableListOf("", "", "", ""),
    val mood: Int = 0,
    var imageUri: Uri? = null,
    val photoNumber: Int = 0,
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