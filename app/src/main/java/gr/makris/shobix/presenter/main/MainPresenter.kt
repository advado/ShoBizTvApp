package gr.makris.shobix.presenter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.HorizontalGridView
import androidx.leanback.widget.Presenter
import gr.makris.shobix.data.VideoData
import gr.makris.shobix.databinding.VideosItemBinding

class MainPresenter : Presenter() {


    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        parent?.findViewById<HorizontalGridView>(androidx.leanback.R.id.row_content).let {
            it?.setItemSpacing(20)
            it?.setPadding(getStartPadding(parent), 0, 60, 0)
        }

        val binding = VideosItemBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val binding = VideosItemBinding.bind(viewHolder?.view ?: return)
        val videoData = (item as? VideoData) ?: return

        binding.itemTitle.text = videoData.description
        binding.imageView.setImageResource(videoData.imageResource)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }

    private fun getStartPadding(parent: ViewGroup?): Int {
        val width = parent?.context?.resources?.displayMetrics?.widthPixels ?: 0
        return (width * 0.05).toInt()
    }
}