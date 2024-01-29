package com.mertozan.membox.domain.usecase

import com.mertozan.membox.model.Memory
import com.mertozan.membox.domain.repository.MemRepository
import javax.inject.Inject


class GetAllNetworkMemoriesUseCase @Inject constructor(
    val memRepository: MemRepository,
) {
    operator fun invoke(): List<Memory> = memRepository.getAllMemoriesList()
}