package com.mertozan.membox.domain

import com.mertozan.membox.repository.MemRepository
import javax.inject.Inject

class GetSingleMemoryUseCase @Inject constructor(
    val memRepository: MemRepository
) {
    operator fun invoke(title: String) =
        memRepository.getMemoryByTitleFromLocal(title)
}