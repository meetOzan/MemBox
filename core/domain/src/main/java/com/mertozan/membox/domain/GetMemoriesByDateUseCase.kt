package com.mertozan.membox.domain

import com.mertozan.membox.MemRepository
import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.model.Memory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemoriesByDateUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(date: String): Flow<ResponseState<List<Memory>>> =
        memRepository.getMemoryByDate(date)
}