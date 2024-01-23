package com.mertozan.membox.domain

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.model.Memory
import com.mertozan.membox.repository.MemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemoryByNameUseCase @Inject constructor(
    val memRepository: MemRepository
){
    operator fun invoke(title: String) : Flow<ResponseState<Memory>> {
        return memRepository.getMemoryByTitle(title)
    }
}