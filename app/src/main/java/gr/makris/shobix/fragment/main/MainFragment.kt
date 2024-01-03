package gr.makris.shobix.fragment.main

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ClassPresenterSelector
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.RowHeaderPresenter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import gr.makris.shobix.R
import gr.makris.shobix.data.VideoData
import gr.makris.shobix.presenter.header.CustomHeaderPresenter
import gr.makris.shobix.presenter.main.MainPresenter
import gr.makris.shobix.viewModel.MainFragmentViewModel


class MainFragment : RowsSupportFragment() {

    private lateinit var rootAdapter: ArrayObjectAdapter
    private lateinit var viewModel: MainFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]

        rootAdapter = ArrayObjectAdapter(createPresenterSelector())
        adapter = rootAdapter

        collectViewModel()
        viewModel.fetchVideos()
    }

    private fun collectViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                handleViewModelState(it)
            }
        }
    }

    private fun handleViewModelState(state: MainFragmentViewModel.UIState) {
        when (state) {
            MainFragmentViewModel.UIState.Loading -> {}
            MainFragmentViewModel.UIState.None -> {}
            is MainFragmentViewModel.UIState.ListReady -> {
                bindData(state.videosList)
            }
        }
    }

    private fun bindData(list: ArrayList<VideoData>) {
        val headerItem = HeaderItem(0, getString(R.string.videos_list_title))
        val mainPresenter = MainPresenter()
        val mainAdapter = ArrayObjectAdapter(mainPresenter)

        list.forEach {
            mainAdapter.add(it)
        }

        val listRow = ListRow(headerItem, mainAdapter)
        rootAdapter.add(listRow)
    }

    private fun createPresenterSelector() =
        ClassPresenterSelector().apply {
            // 1
            addClassPresenter(
                RowHeaderPresenter::class.java,
                CustomHeaderPresenter()
            )

            // 2
            addClassPresenter(
                ListRow::class.java,
                ListRowPresenter(FocusHighlight.ZOOM_FACTOR_XSMALL).apply {
                    this.shadowEnabled = false
                }
            )
        }




    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}