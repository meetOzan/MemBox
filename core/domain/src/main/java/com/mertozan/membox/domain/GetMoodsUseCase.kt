package com.mertozan.membox.domain

import com.mertozan.membox.repository.MemRepository
import com.mertozan.membox.core.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoodsUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(): Flow<ResponseState<Map<String, Float>>> {
        return memRepository.getMoodsFromMemories()
    }
}