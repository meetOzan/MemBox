package com.mertozan.membox.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel(){

    private val _profileUiState = MutableStateFlow(ProfileUiState.initial())
    val profileUiState = _profileUiState.asStateFlow()


}

data class ProfileUiState(
    val isLoading : Boolean = false,
    val isSuccess : Boolean = false,
    val isError : Boolean = false,
    val errorMessage : String = "",
    val profileName: String = "",
    val profileTotalMemory: String = "",
    val profileMemoryStreak: String = "",
    val moodValueList : MutableList<Float> = mutableListOf(0.1f,0.1f,0.1f,0.1f,0.1f),
){
    companion object{
        fun initial() = ProfileUiState(isLoading = true)
    }
}