package gr.makris.shobix.repository

import gr.makris.shobix.data.VideoData

interface FetchVideoContract {

    fun fetchVideos(): ArrayList<VideoData>
}