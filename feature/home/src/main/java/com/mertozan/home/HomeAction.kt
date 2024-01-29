package com.mertozan.home

import com.mertozan.membox.model.Memory
import com.mertozan.membox.source.network.dto.User

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
}