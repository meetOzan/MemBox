package com.mertozan.membox.domain.usecase

import com.mertozan.membox.model.Memory
import com.mertozan.membox.domain.repository.MemRepository
import javax.inject.Inject

class AddToLocalUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(memory: Memory) = memRepository.addMemoryToLocal(memory)
}