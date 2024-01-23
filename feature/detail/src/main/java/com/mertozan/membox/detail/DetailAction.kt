package com.mertozan.membox.detail

sealed class DetailAction {
    data class ChangeMemoryTitle(val memoryName: String) : DetailAction()
    data class ChangeMemoryContent(val memoryContent: String) : DetailAction()
    data object GetMemoryByName : DetailAction()
}