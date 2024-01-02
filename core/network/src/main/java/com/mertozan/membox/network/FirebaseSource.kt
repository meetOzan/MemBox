package com.mertozan.membox.network

import com.mertozan.membox.network.dto.User

interface FirebaseSource{

    fun signUpUserWithEmailAndPassword(user: User, onNavigate: () -> Unit)

    fun signInWithEmailAndPassword(user: User, onNavigate: () -> Unit)

    fun signOut()

    fun isUserSignedIn() : Boolean

}