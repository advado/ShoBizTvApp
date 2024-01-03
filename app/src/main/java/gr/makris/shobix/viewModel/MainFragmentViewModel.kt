package gr.makris.shobix.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gr.makris.shobix.data.VideoData
import gr.makris.shobix.repository.FetchVideoRepository
import gr.makris.shobix.useCase.FetchVideosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainFragmentViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState.None)
    val uiState = _uiState.asStateFlow()
    private val fetchVideosUseCase = FetchVideosUseCase(repository = FetchVideoRepository())

    fun fetchVideos() {
        viewModelScope.launch {
            val videosList = fetchVideosUseCase.invoke()
            _uiState.value = UIState.ListReady(videosList = videosList)
        }
    }

    sealed class UIState {
        data object None: UIState()
        data object Loading: UIState()
        data class ListReady(val videosList: ArrayList<VideoData>): UIState()
    }
}