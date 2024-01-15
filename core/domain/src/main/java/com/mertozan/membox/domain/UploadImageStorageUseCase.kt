package com.mertozan.membox.domain

import android.content.Context
import android.net.Uri
import com.mertozan.membox.repository.MemRepository
import com.mertozan.membox.core.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadImageStorageUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(
        uri: Uri,
        context: Context,
        onSuccess: (String, String) -> Unit,
        onFailure: (String) -> Unit,
    ): Flow<ResponseState<Unit>> {
        return memRepository.uploadImageStorage(
            uri = uri,
            context = context,
            onSuccess = onSuccess,
            onFailure = onFailure,
        )
    }
}