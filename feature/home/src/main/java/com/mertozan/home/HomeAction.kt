package com.mertozan.home

sealed class HomeAction{
    data object GetAllMemories : HomeAction()
    data object GetLocalMemories : HomeAction()
}