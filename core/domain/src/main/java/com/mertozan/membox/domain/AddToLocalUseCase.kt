package com.mertozan.membox.domain

import com.mertozan.membox.model.Memory
import com.mertozan.membox.repository.MemRepository
import javax.inject.Inject

class AddToLocalUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(memory: Memory) = memRepository.addMemoryToLocal(memory)
}