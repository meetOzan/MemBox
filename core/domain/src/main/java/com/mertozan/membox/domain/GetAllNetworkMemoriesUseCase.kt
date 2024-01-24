package com.mertozan.membox.domain

import com.mertozan.membox.model.Memory
import com.mertozan.membox.repository.MemRepository
import javax.inject.Inject


class GetAllNetworkMemoriesUseCase @Inject constructor(
    val memRepository: MemRepository,
) {

    operator fun invoke(): List<Memory> = memRepository.getAllMemoriesList()

}