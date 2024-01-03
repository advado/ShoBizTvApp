package gr.makris.shobix.useCase

import gr.makris.shobix.repository.FetchVideoContract

class FetchVideosUseCase(private val repository: FetchVideoContract) {

    operator fun invoke() = repository.fetchVideos()
}