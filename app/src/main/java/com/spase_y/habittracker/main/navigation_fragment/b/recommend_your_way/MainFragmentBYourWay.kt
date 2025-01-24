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


val horizontalItems = listOf(
    HorizontalRecommendItem("Ритуалы перед\nсном", R.drawable.b_list1,
        FullRoadTopicData(
            R.drawable.sleep_girl,
            "Научно доказано, что определенные\nритуалы перед сном позволяют справится с нарушениями сна и избыточным стрессом",
            listOf(
                Pair(R.drawable.habit_icon_176, "Попращайтесь с\nбессонницей "),
                Pair(R.drawable.habit_icon_151, "Засыпайте\nбыстрее и\nкрепче"),
                Pair(R.drawable.habit_icon_27, "Больше сил\nи творческой\nэнергии"),
                Pair(R.drawable.abc_ic_menu_paste_mtrl_am_alpha, "Здоровое\nрасписание"),
            ),
            listOf(
                "Укрепите душевное здоровье",
                "Укрепление связей с семьей",
                "Более эффективная работа мозга",

                ),
        ),
        "8"
    ),
    HorizontalRecommendItem("Ритуалы перед\nсном", R.drawable.b_list1,
        FullRoadTopicData(
            R.drawable.sleep_girl,
            "Научно доказано, что определенные\nритуалы перед сном позволяют справится с нарушениями сна и избыточным стрессом",
            listOf(
                Pair(R.drawable.habit_icon_176, "Попращайтесь с\nбессонницей "),
                Pair(R.drawable.habit_icon_151, "Засыпайте\nбыстрее и\nкрепче"),
                Pair(R.drawable.habit_icon_27, "Больше сил\nи творческой\nэнергии"),
                Pair(R.drawable.abc_ic_menu_paste_mtrl_am_alpha, "Здоровое\nрасписание"),
            ),
            listOf(
                "Укрепите душевное здоровье",
                "Укрепление связей с семьей",
                "Более эффективная работа мозга",

                ),
        ),
        "30"
    ),
)

val verticalItems = listOf(
    VerticalRecommendItem("Ритуалы перед\nсном", R.drawable.b_list1,
        FullRoadTopicData(
            R.drawable.sleep_girl,
            "Научно доказано, что определенные\nритуалы перед сном позволяют справится с нарушениями сна и избыточным стрессом",
            listOf(
                Pair(R.drawable.habit_icon_176, "Попращайтесь с\nбессонницей "),
                Pair(R.drawable.habit_icon_151, "Засыпайте\nбыстрее и\nкрепче"),
                Pair(R.drawable.habit_icon_27, "Больше сил\nи творческой\nэнергии"),
                Pair(R.drawable.abc_ic_menu_paste_mtrl_am_alpha, "Здоровое\nрасписание"),
            ),
            listOf(
                "Укрепите душевное здоровье",
                "Укрепление связей с семьей",
                "Более эффективная работа мозга",

            ),
        )
    ),

    VerticalRecommendItem("Попращайтесь с\nсахаром", R.drawable.b_list_2,
        FullRoadTopicData(
            R.drawable.b_big_list2,
            "Доказано, что это отлично влияет на здоровье - от кожи до общего состояния!",
            listOf(
                Pair(R.drawable.ic_icon_journeystg_finish, "Улучшится\nсостояние кожи"),
                Pair(R.drawable.habit_icon_9, "Профилактика\nожирения"),
                Pair(R.drawable.ic_icon_general_check, "Профилактика\nдиабета"),
                Pair(R.drawable.habit_icon_97, "Ниже давление"),
            ),
            listOf(
                "Меньше хронических восполений",
                "Меньше зубного налета и кариеса",
                "Профилактика ожирения печени\nнеалкогольного генеза",

            ),
        )
    ),
    VerticalRecommendItem("Медитация для\nгармонии в душе", R.drawable.b_list_3,
        FullRoadTopicData(
            R.drawable.b_big_list3,
            "Медитация - это связь между внешним и вашим внутренним миром",
            listOf(
                Pair(R.drawable.ic_icon_general_circlecheck, "Самосознание\nи самоуважение"),
                Pair(R.drawable.habit_icon_61, "Меньше\nнапряжения\nи боли"),
                Pair(R.drawable.ic_icon_journeystg_solid, "Боритесь с\nзависимостями"),
                Pair(R.drawable.habit_icon_100, "Повысьте\nустойчивость к стрессу"),
            ),
            listOf(
                "Замедлите старение мозга",
                "Лучшая концентрация внимания",
                "Управляйте своими эмоциями",
                "text4",
            ),
        )
    ),
    VerticalRecommendItem("Наши пушистые\nдрузья", R.drawable.b_list_4,
        FullRoadTopicData(
            R.drawable.b_big_list4,
            "Домашние животные - наши самые верные спутники. Они всегда поддержат и помогут перенести социальную изоляцию",
            listOf(
                Pair(R.drawable.habit_icon_97, "Меньше\nдепрессии и\nодиночества"),
                Pair(R.drawable.ic_icon_journeystg_adapt, "Здоровее\nсердце"),
                Pair(R.drawable.ic_icon_journeystg_finish, "Меньше стресса\nи беспокойств"),
                Pair(R.drawable.habit_icon_101, "Больше\nобщения с\nдругими людьми"),
            ),
            listOf(
                "Больше времени вне дома",
                "Сопереживание и уважение",
                "Терапия и эмоциональная поддержка",
                "text4",
            ),
        )
    ),
)