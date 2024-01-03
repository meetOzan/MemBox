package com.mertozan.home

import androidx.lifecycle.ViewModel
import com.mertozan.membox.model.Memory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {


}

data class HomeUiState(
    val isLoading: Boolean = false,
    val characters: List<Memory> = emptyList(),
    val error: String = ""
)