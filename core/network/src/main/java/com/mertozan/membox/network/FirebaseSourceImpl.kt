package com.mertozan.membox.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mertozan.membox.network.dto.User
import javax.inject.Inject

class FirebaseSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : FirebaseSource {

    override fun signUpUserWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                val currentUser = auth.currentUser
                val userMap = hashMapOf(
                    "id" to currentUser?.uid.toString(),
                    "email" to user.email,
                    "password" to user.password,
                    "name" to user.name,
                    "surname" to user.surname
                )
                firestore.collection("users").document(currentUser?.uid.toString())
                    .set(userMap).addOnCompleteListener {
                        if (it.isSuccessful) {
                            onNavigate()
                        } else {
                            throw RuntimeException("User could not be created.")
                        }
                    }
            } else {
                Log.e("Firebase Auth.", "createUserWithEmail:failure", it.exception)
            }
        }
    }

    override fun signInWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ) {
        auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                onNavigate()
            } else {
                Log.e("Firebase Auth.", "signInWithEmail:failure", it.exception)
            }
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun isUserSignedIn() : Boolean {
        return auth.currentUser != null
    }

}