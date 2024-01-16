package com.mertozan.membox.domain

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.model.Memory
import com.mertozan.membox.repository.MemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalMemoriesUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke() : Flow<ResponseState<List<Memory>>> = memRepository.getAllLocalMemories()
}