package com.mertozan.membox.profile

sealed class ProfileAction {
    data object GetMoods : ProfileAction()
    data object DeleteAllMemoriesNetwork : ProfileAction()
    data object DeleteAllMemoriesFromLocal : ProfileAction()
    data object ChangeDialogState : ProfileAction()
    data object ChangeSettingsState : ProfileAction()
    data object SignOut : ProfileAction()
    data object GetLocalMemories : ProfileAction()
    data object DeleteUser : ProfileAction()
    data object GetLocalUser : ProfileAction()
}