package com.mertozan.membox.domain

import com.mertozan.membox.model.Memory
import com.mertozan.membox.repository.MemRepository
import javax.inject.Inject

class TransferToLocalUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(memoryList: List<Memory>) =
        memRepository.transferMemoriesToLocal(memoryList)
}