package gr.makris.shobix.presenter.header

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import gr.makris.shobix.R

class CustomHeaderPresenter : RowHeaderPresenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        // Inflate a custom layout for the header
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.videos_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder?, item: Any?) {
        super.onBindViewHolder(viewHolder, item)
    }



//    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
//        val headerItem = item as? HeaderItem
//        val headerView = viewHolder.view as? TextView
//        headerView?.text = headerItem?.name
//        // Apply any additional styling if needed
//    }

//    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
//        // Optional: Clean up resources if needed
//    }
}