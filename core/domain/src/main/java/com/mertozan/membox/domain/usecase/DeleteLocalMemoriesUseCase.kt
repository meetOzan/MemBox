package com.mertozan.membox.domain.usecase

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.domain.repository.MemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteLocalMemoriesUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke() : Flow<ResponseState<Unit>> = memRepository.deleteAllMemoriesFromLocal()
}