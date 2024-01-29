package com.mertozan.membox.domain.usecase

import com.mertozan.membox.domain.repository.MemRepository
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.model.Memory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMemoriesUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(): Flow<ResponseState<List<Memory>>> {
        return memRepository.getAllMemories()
    }
}