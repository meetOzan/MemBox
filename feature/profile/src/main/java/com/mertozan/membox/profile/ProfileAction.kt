package com.mertozan.membox.profile

sealed class ProfileAction {
    data object GetAllMemories : ProfileAction()
    data object GetMoods : ProfileAction()
    data object DeleteAllMemoriesNetwork : ProfileAction()
    data object DeleteAllMemoriesFromLocal : ProfileAction()
    data object ChangeDialogState : ProfileAction()
    data object GetLocalMemories : ProfileAction()
}