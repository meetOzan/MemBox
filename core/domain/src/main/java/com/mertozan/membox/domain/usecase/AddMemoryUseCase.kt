package com.mertozan.membox.domain.usecase

import com.mertozan.membox.domain.repository.MemRepository
import com.mertozan.membox.model.Memory
import javax.inject.Inject

class AddMemoryUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(
        memory: Memory,
        onNavigate: () -> Unit,
    ) = memRepository.addMemory(memory, onNavigate)
}