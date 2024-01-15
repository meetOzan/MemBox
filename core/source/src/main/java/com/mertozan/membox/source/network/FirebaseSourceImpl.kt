package com.mertozan.membox.source.network

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import com.mertozan.membox.core.mapper.uriToBitmap
import com.mertozan.membox.model.Memory
import com.mertozan.membox.source.network.dto.User
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class FirebaseSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
) : FirebaseSource {

    override fun signUpUserWithEmailAndPassword(
        user: User,
        onNavigate: () -> Unit,
    ) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { response ->
                if (response.isSuccessful) {
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
                    Log.e("Firebase Auth.", "createUserWithEmail:failure", response.exception)
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

    override fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun addMemory(memory: Memory, onNavigate: () -> Unit) {
        val currentUser = auth.currentUser
        val memoryMap = hashMapOf(
            "id" to currentUser?.uid.toString(),
            "title" to memory.title,
            "content" to memory.content,
            "date" to memory.date,
            "image" to memory.image,
            "mood" to memory.mood,
            "moodName" to memory.moodName
        )
        firestore.collection("users").document(currentUser?.uid.toString())
            .collection("memories").document(memory.title)
            .set(memoryMap).addOnCompleteListener {
                if (it.isSuccessful) {
                    onNavigate()
                    Log.d("Firebase Firestore", "Memory added.")
                } else {
                    throw RuntimeException("Memory could not be added.")
                }
            }
    }

    override fun getAllMemories(): List<Memory> {
        val currentUser = auth.currentUser
        val memories = mutableListOf<Memory>()
        firestore.collection("users").document(currentUser?.uid.toString())
            .collection("memories").addSnapshotListener { querySnapshot, firestoreException ->
                firestoreException?.let {
                    throw RuntimeException(it.message)
                }
                querySnapshot?.let {
                    for (document in it) {
                        val memory = document.toObject<Memory>()
                        Log.d("Firestore", "${memory.title} => ${memory.content}")
                        memories.add(memory)
                    }
                }
            }
        return memories
    }

    override fun getMemoryByDate(date: String): List<Memory> {
        val currentUser = auth.currentUser
        val memories = mutableListOf<Memory>()
        firestore.collection("users").document(currentUser?.uid.toString())
            .collection("memories").whereEqualTo("date", date).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        val memory = Memory(
                            title = document.data["title"].toString(),
                            content = document.data["content"].toString(),
                            date = document.data["date"].toString(),
                            image = document.data["image"] as List<String>,
                            mood = document.data["mood"].toString().toInt(),
                            moodName = document.data["moodName"].toString()
                        )
                        memories.add(memory)
                    }
                } else {
                    throw RuntimeException("Memory could not be fetched.")
                }
            }
        return memories
    }

    override fun uploadImageToStorage(
        uri: Uri,
        context: Context,
        onSuccess: (String, String) -> Unit,
        onFailure: (String) -> Unit,
    ) {
        val uuid = java.util.UUID.randomUUID()
        val imageName = "$uuid.jpg"

        val contentResolver = context.contentResolver

        val reference = storage.reference.child("images").child(imageName)

        val byteArrayOutputStream = ByteArrayOutputStream()
        val mBitmap = uri.uriToBitmap(contentResolver)
        mBitmap?.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
        val data = byteArrayOutputStream.toByteArray()

        reference.putBytes(data).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener { uri ->
                onSuccess(uri.toString(), imageName)
            }.addOnFailureListener { exception ->
                onFailure(exception.message.orEmpty())
            }
        }.addOnFailureListener {
            onFailure(it.message.orEmpty())
        }
    }

    override fun uploadImageToFirestore(
        imagesUrl: List<String>,
        imageName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit,
    ) {
        val currentUser = auth.currentUser
        val imageMap = hashMapOf(
            "imageUrl" to imagesUrl,
            "imageName" to imageName
        )
        firestore.collection("users").document(currentUser?.uid.toString())
            .collection("images").document(imageName)
            .set(imageMap).addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccess()
                    Log.d("Firebase Firestore", "Image added.")
                } else {
                    onFailure(it.exception?.message.orEmpty())
                }
            }
    }

    override fun getMoodsFromMemories(): Map<String, Float> {
        val currentUser = auth.currentUser
        val moodMap = mutableMapOf<String, Float>(
            "Love" to 0.1f,
            "Happy" to 0.1f,
            "Sad" to 0.1f,
            "Angry" to 0.1f,
            "AngryCry" to 0.1f,
        )
        firestore.collection("users").document(currentUser?.uid.toString())
            .collection("memories").addSnapshotListener { querySnapshot, firestoreException ->
                firestoreException?.let {
                    throw RuntimeException(it.message)
                }
                querySnapshot?.let {
                    for (document in it) {
                        val memory = document.toObject<Memory>()
                        memory.moodName.let { mood ->
                            if (moodMap.containsKey(mood)) {
                                moodMap[mood] = moodMap[mood]!! + 1f
                            } else {
                                moodMap[mood] = 0.1f
                            }
                            Log.e("FirebaseSourceImpl", "getMoodsFromMemories: $moodMap")
                        }
                    }
                }
            }
        return moodMap
    }

    override fun deleteAllMemories() {
        val currentUser = auth.currentUser
        firestore.collection("users").document(currentUser?.uid.toString())
            .collection("memories").get().addOnCompleteListener {
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        document.reference.delete()
                    }
                } else {
                    throw RuntimeException(it.exception?.message.orEmpty())
                }
            }
    }
}