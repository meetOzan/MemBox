package com.mertozan.home

import com.mertozan.membox.model.Memory

sealed class HomeAction{
    data object GetAllMemories : HomeAction()
    data object GetLocalMemories : HomeAction()
    data class TransferMemoriesToLocal(val memoryList: List<Memory>) : HomeAction()
    data object DeleteAllMemories : HomeAction()

    data object DeleteLocalMemories : HomeAction()
}