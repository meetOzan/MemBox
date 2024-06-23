package com.mertozan.home

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset
import com.mertozan.membox.model.Memory
import com.mertozan.membox.model.User

sealed class HomeAction {
    data object GetAllMemories : HomeAction()
    data object GetLocalMemories : HomeAction()
    data class TransferMemoriesToLocal(val memoryList: List<Memory>) : HomeAction()
    data object DeleteAllMemories : HomeAction()
    data object DeleteLocalMemories : HomeAction()
    data object GetLocalUser : HomeAction()
    data object GetUserNetwork : HomeAction()
    data class TransferUserToLocal(val user: User) : HomeAction()
    data object DeleteLocalUser : HomeAction()
    data class ChangeNotificationStatus(val newStatus: Boolean) : HomeAction()
    data object GetCurrentDate : HomeAction()
    data class ChangePopupMenuVisibility(val popUpVisibility: Boolean) :
        HomeAction()

    data class ChangePopupMenuOffset(val offset: DpOffset) : HomeAction()
}