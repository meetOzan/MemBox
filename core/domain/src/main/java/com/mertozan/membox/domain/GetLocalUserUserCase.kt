package com.mertozan.membox.domain

import com.mertozan.membox.core.ResponseState
import com.mertozan.membox.repository.UserRepository
import com.mertozan.membox.source.network.dto.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalUserUserCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() : Flow<ResponseState<User?>> = userRepository.getLocalUser()

}