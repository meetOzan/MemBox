package com.mertozan.membox.domain

import com.mertozan.membox.MemRepository
import com.mertozan.membox.core.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsUserSigned @Inject constructor(
    private val memRepository: MemRepository
) {
    operator fun invoke(): Flow<ResponseState<Boolean>> {
        return memRepository.isUserSignedIn()
    }
}