package com.mertozan.membox.domain.usecase

import com.mertozan.membox.domain.repository.MemRepository
import javax.inject.Inject

class GetSingleMemoryUseCase @Inject constructor(
    val memRepository: MemRepository
) {
    operator fun invoke(title: String) =
        memRepository.getMemoryByTitleFromLocal(title)
}