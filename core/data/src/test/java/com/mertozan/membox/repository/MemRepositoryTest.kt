package com.mertozan.membox.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.mertozan.membox.domain.infrastructure.ResourceProvider
import com.mertozan.membox.domain.repository.MemRepository
import com.mertozan.membox.domain.source.FirebaseSource
import com.mertozan.membox.domain.source.LocalSource
import com.mertozan.membox.source.local.LocalSourceImpl
import com.mertozan.membox.source.local.dao.MemoryDao
import com.mertozan.membox.source.local.dao.UserDao
import com.mertozan.membox.source.network.FirebaseSourceImpl
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class MemRepositoryTest {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var firebaseFirestore: FirebaseFirestore

    @Mock
    private lateinit var firebaseStorage: FirebaseStorage

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var memoryDao: MemoryDao

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private lateinit var firebaseSource: FirebaseSource
    private lateinit var localSource: LocalSource

    private lateinit var memRepository: MemRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        firebaseSource = FirebaseSourceImpl(
            firebaseAuth,
            firebaseFirestore,
            firebaseStorage
        )
        localSource = LocalSourceImpl(
            userDao,
            memoryDao,
            resourceProvider
        )
        memRepository = MemRepositoryImpl(
            firebaseSource,
            localSource
        )
    }


}