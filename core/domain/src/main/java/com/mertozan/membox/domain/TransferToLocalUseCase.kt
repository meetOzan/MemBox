package com.mertozan.membox.domain

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.repository.MemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransferToLocalUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(): Flow<ResponseState<Unit>> = memRepository.transferMemoriesToLocal()
}