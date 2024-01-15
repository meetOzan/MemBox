package com.mertozan.membox.profile

sealed class ProfileAction {
    data object GetAllMemories : ProfileAction()
    data object GetMoods : ProfileAction()
    data object DeleteAllMemories : ProfileAction()
    data object ChangeDialogState : ProfileAction()
}