package com.spase_y.habittracker.main.navigation_fragment.b.recommend_your_way

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentMainBYourWayBinding


class MainFragmentBYourWay : Fragment() {
    private var _binding: FragmentMainBYourWayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBYourWayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verticalItems = listOf(
            VerticalRecommendItem(
                getString(R.string.rituals_before_sleep),
                R.drawable.b_list1,
                FullRoadTopicData(
                    R.drawable.sleep_girl,
                    getString(R.string.scientific_proof),
                    listOf(
                        Pair(R.drawable.habit_icon_176, getString(R.string.goodbye_insomnia)),
                        Pair(R.drawable.habit_icon_151, getString(R.string.fall_asleep_quicker_and_deeper)),
                        Pair(R.drawable.habit_icon_27, getString(R.string.more_energy_and_creativity)),
                        Pair(R.drawable.abc_ic_menu_paste_mtrl_am_alpha, getString(R.string.healthy_schedule)),
                    ),
                    listOf(
                        getString(R.string.strengthen_mental_health),
                        getString(R.string.strengthen_family_bonds),
                        getString(R.string.more_brain_efficiency),
                    ),
                )
            ),
            VerticalRecommendItem(
                getString(R.string.goodbye_sugar),
                R.drawable.b_list_2,
                FullRoadTopicData(
                    R.drawable.b_big_list2,
                    getString(R.string.sugar_health_benefits),
                    listOf(
                        Pair(R.drawable.ic_icon_journeystg_finish, getString(R.string.skin_condition)),
                        Pair(R.drawable.habit_icon_9, getString(R.string.obesity_prevention)),
                        Pair(R.drawable.ic_icon_general_check, getString(R.string.diabetes_prevention)),
                        Pair(R.drawable.habit_icon_97, getString(R.string.lower_blood_pressure)),
                    ),
                    listOf(
                        getString(R.string.reduced_chronic_inflammation),
                        getString(R.string.less_plaque_and_caries),
                        getString(R.string.liver_fat_prevention),
                    ),
                )
            ),
            VerticalRecommendItem(
                getString(R.string.meditation_for_harmony),
                R.drawable.b_list_3,
                FullRoadTopicData(
                    R.drawable.b_big_list3,
                    getString(R.string.meditation_connection),
                    listOf(
                        Pair(R.drawable.ic_icon_general_circlecheck, getString(R.string.self_awareness_and_respect)),
                        Pair(R.drawable.habit_icon_61, getString(R.string.reduced_stress_and_pain)),
                        Pair(R.drawable.ic_icon_journeystg_solid, getString(R.string.fight_addictions)),
                        Pair(R.drawable.habit_icon_100, getString(R.string.increase_stress_resilience)),
                    ),
                    listOf(
                        getString(R.string.slow_brain_aging),
                        getString(R.string.better_concentration),
                        getString(R.string.manage_emotions),
                        getString(R.string.placeholder_text),
                    ),
                )
            ),
            VerticalRecommendItem(
                getString(R.string.our_furry_friends),
                R.drawable.b_list_4,
                FullRoadTopicData(
                    R.drawable.b_big_list4,
                    getString(R.string.pets_health_benefits),
                    listOf(
                        Pair(R.drawable.habit_icon_97, getString(R.string.less_depression_and_loneliness)),
                        Pair(R.drawable.ic_icon_journeystg_adapt, getString(R.string.healthier_heart)),
                        Pair(R.drawable.ic_icon_journeystg_finish, getString(R.string.reduced_stress_and_anxiety)),
                        Pair(R.drawable.habit_icon_101, getString(R.string.more_social_interaction)),
                    ),
                    listOf(
                        getString(R.string.more_time_outdoors),
                        getString(R.string.empathy_and_respect),
                        getString(R.string.therapy_and_emotional_support),
                        getString(R.string.placeholder_text),
                    ),
                )
            )
        )

        val horizontalItems = listOf(
            HorizontalRecommendItem(
                getString(R.string.walk_every_day), R.drawable.bg_horizont_1,
                FullRoadTopicData(
                    R.drawable.bg_horizont_1,
                    getString(R.string.walk_every_day_desc),
                    listOf(
                        Pair(R.drawable.habit_icon_99, getString(R.string.healthy_bmi)),
                        Pair(R.drawable.habit_icon_11, getString(R.string.less_joint_pain)),
                        Pair(R.drawable.habit_icon_16, getString(R.string.lower_blood_sugar)),
                        Pair(R.drawable.habit_icon_32, getString(R.string.better_cardiovascular)),
                    ),
                    listOf(
                        getString(R.string.strong_immune_system),
                        getString(R.string.longer_life),
                        getString(R.string.varicose_prevention),
                    ),
                ),
                "30"
            ),
            HorizontalRecommendItem(
                getString(R.string.energetic_morning), R.drawable.bg_horizont_2,
                FullRoadTopicData(
                    R.drawable.bg_horizont_2,
                    getString(R.string.energetic_morning_desc),
                    listOf(
                        Pair(R.drawable.habit_icon_12, getString(R.string.wake_up_early)),
                        Pair(R.drawable.habit_icon_202, getString(R.string.self_confidence)),
                        Pair(R.drawable.habit_icon_66, getString(R.string.more_energy)),
                        Pair(R.drawable.ic_icon_journeystg_adapt, getString(R.string.healthy_schedule)),
                    ),
                    listOf(
                        getString(R.string.healthy_lifestyle),
                        getString(R.string.balanced_immunity),
                        getString(R.string.brain_efficiency),
                    ),
                ),
                "30"
            ),
            HorizontalRecommendItem(
                getString(R.string.stay_fit_office), R.drawable.bg_horizont_3,
                FullRoadTopicData(
                    R.drawable.bg_horizont_3,
                    getString(R.string.stay_fit_office_desc),
                    listOf(
                        Pair(R.drawable.habit_icon_12, getString(R.string.less_sitting)),
                        Pair(R.drawable.habit_icon_81, getString(R.string.work_more_effectively)),
                        Pair(R.drawable.ic_icon_journeystg_adapt, getString(R.string.disease_prevention)),
                        Pair(R.drawable.habit_icon_32, getString(R.string.less_chronic_stress)),
                    ),
                    listOf(
                        getString(R.string.better_memory),
                        getString(R.string.use_breaks_effectively),
                        getString(R.string.confidence_and_mood),
                    ),
                ),
                "30"
            ),
        )



        // Данные для RecyclerView
        val adapter = HorizontalRecommendAdapter(horizontalItems){item ->
            val fragment = TopicDetailsFragment()
            fragment.arguments =
                bundleOf(
                    Pair("title", item.title),
                    Pair("data", Gson().toJson(item.data)),
                )
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp,fragment)
                .addToBackStack(null)
                .commit()
        }
        binding.rvRecommend.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecommend.adapter = adapter

        val sidePadding = resources.getDimensionPixelSize(R.dimen.recycler_side_padding)
        binding.rvRecommend.addItemDecoration(EdgeSpacingItemDecoration(sidePadding))


        val verticalAdapter = VerticalRecommendAdapter(verticalItems){ item ->
            val fragment = TopicDetailsFragment()
            fragment.arguments =
                bundleOf(
                    Pair("title", item.title),
                    Pair("data", Gson().toJson(item.data)),
                )
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, fragment)
                .addToBackStack(null)
                .commit()
        }
        binding.rvVerticalRecommend.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvVerticalRecommend.adapter = verticalAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

val sample = FullRoadTopicData(
    R.drawable.sleep_girl,
    "some text",
    listOf(
        Pair(R.drawable.abc_ic_menu_cut_mtrl_alpha, "text1"),
        Pair(R.drawable.abc_ic_menu_cut_mtrl_alpha, "text2"),
        Pair(R.drawable.abc_ic_ab_back_material, "text3"),
        Pair(R.drawable.abc_ic_menu_cut_mtrl_alpha, "text4"),
    ),
    listOf(
        "text1",
        "text2",
        "text3",
        "text4",
    ),
)




