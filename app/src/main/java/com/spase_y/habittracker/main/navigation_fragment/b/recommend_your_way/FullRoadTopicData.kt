package com.spase_y.habittracker.main.navigation_fragment.b.recommend_your_way

data class FullRoadTopicData(
    val drawableId: Int,
    val description: String,
    val topicResults: List<Pair<Int, String>>,
    val futureExpectations: List<String>,
)