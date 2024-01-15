package com.mertozan.membox.domain

import com.mertozan.membox.repository.MemRepository
import com.mertozan.membox.core.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAllMemoriesUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(): Flow<ResponseState<Unit>> = memRepository.deleteAllMemories()
}