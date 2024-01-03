package gr.makris.shobix.repository

import gr.makris.shobix.R
import gr.makris.shobix.data.VideoData

class FetchVideoRepository: FetchVideoContract {

    override fun fetchVideos(): ArrayList<VideoData> {
        return arrayListOf(
            VideoData(description = "ShoBiz TV Opening Event", imageResource = R.drawable.image1),
            VideoData(description = "Sun Up TV Show Ep. 32", imageResource = R.drawable.image2),
            VideoData(description = "Sun Up TV Show Ep. 31", imageResource = R.drawable.image3),
            VideoData(description = "ShoBiz TV Opening Event", imageResource = R.drawable.image1),
            VideoData(description = "Sun Up TV Show Ep. 32", imageResource = R.drawable.image2),
            VideoData(description = "Sun Up TV Show Ep. 31", imageResource = R.drawable.image3)
        )
    }
}