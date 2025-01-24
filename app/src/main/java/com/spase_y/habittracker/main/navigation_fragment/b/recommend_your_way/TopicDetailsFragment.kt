package com.spase_y.habittracker.main.navigation_fragment.b.recommend_your_way

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentDetailsFromHorizontalBinding

class TopicDetailsFragment : Fragment() {
    private var _binding: FragmentDetailsFromHorizontalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsFromHorizontalBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = requireArguments().getString("title").toString()
        val data = requireArguments().getString("data").toString()
        val fullRoadTopicData = Gson().fromJson<FullRoadTopicData>(data, FullRoadTopicData::class.java)

        binding.textView.text = title
        binding.textView2.text = fullRoadTopicData.description
        binding.imageView9.setImageResource(fullRoadTopicData.drawableId)

        try {
            binding.ivIcon1.setImageResource(fullRoadTopicData.topicResults[0].first)
            binding.ivIcon2.setImageResource(fullRoadTopicData.topicResults[1].first)
            binding.ivIcon3.setImageResource(fullRoadTopicData.topicResults[2].first)
            binding.ivIcon4.setImageResource(fullRoadTopicData.topicResults[3].first)
        } catch (e : Exception) {}
        try {
            binding.tvText1.text = fullRoadTopicData.topicResults[0].second
            binding.tvText2.text = fullRoadTopicData.topicResults[1].second
            binding.tvText3.text = fullRoadTopicData.topicResults[2].second
            binding.tvText4.text = fullRoadTopicData.topicResults[3].second
        } catch (e : Exception) {}
        try {
            binding.tvTextExplanations1.text = fullRoadTopicData.futureExpectations[0]
            binding.tvTextExplanations2.text = fullRoadTopicData.futureExpectations[1]
            binding.tvTextExplanations3.text = fullRoadTopicData.futureExpectations[2]
        } catch (e : Exception) {}


        binding.ivArrowBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        requireActivity().findViewById<View>(R.id.llNavigation)?.visibility = View.GONE

        binding.ivArrowBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().findViewById<View>(R.id.llNavigation)?.visibility = View.VISIBLE
        _binding = null
    }
}