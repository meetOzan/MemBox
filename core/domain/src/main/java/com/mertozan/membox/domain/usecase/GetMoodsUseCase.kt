package com.mertozan.membox.domain.usecase

import com.mertozan.membox.domain.repository.MemRepository
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