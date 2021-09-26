package com.pranavkonduru.nasaapod.util

import com.pranavkonduru.nasaapod.data.models.NasaNewsItem

object DataUtils {
    fun fetchNasaNewsItemsList(): List<NasaNewsItem> {
        val learningsList = ArrayList<NasaNewsItem>()
        learningsList.add(NasaNewsItem("Near Earth Objects(NEOs)!!!"))
        learningsList.add(NasaNewsItem("Astronomy Pic of the day!!"))
        learningsList.add(NasaNewsItem("Earth Observatory natural Event Tracker(EONET)"))
        learningsList.add(NasaNewsItem("Earth Polychromatic Imaging Camera (EPIC)"))
        learningsList.add(NasaNewsItem("NASA Image and Video Library!!"))
        learningsList.add(NasaNewsItem("Mars Rover Photos!!"))
        learningsList.add(NasaNewsItem("Mars Weather Insights!!"))
        learningsList.add(NasaNewsItem("All about Earth by NASA!!"))
        return learningsList
    }
}