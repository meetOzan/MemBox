package com.mertozan.membox.domain

import com.mertozan.membox.MemRepository
import com.mertozan.membox.core.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UploadImageFirestoreUseCase @Inject constructor(
    private val memRepository: MemRepository,
) {
    operator fun invoke(
        imagesUrl: List<String>,
        imageName: String,
        onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit = { _ -> },
    ) : Flow<ResponseState<Unit>> {
        return memRepository.uploadImageToFirestore(
            imagesUrl = imagesUrl,
            imageName = imageName,
            onSuccess = onSuccess,
            onFailure = onFailure,
        )
    }
}