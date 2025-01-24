package com.spase_y.habittracker.main.navigation_fragment.c

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentMainCHistoryBinding
import com.spase_y.habittracker.main.navigation_fragment.c.history_view_pager.AllHabitsFragment
import com.spase_y.habittracker.main.navigation_fragment.c.history_view_pager.CalendarFragment
import com.spase_y.habittracker.main.navigation_fragment.c.history_view_pager.ProgressFragment
import com.spase_y.habittracker.main.navigation_fragment.d.ShareApp
import com.spase_y.habittracker.main.navigation_fragment.d.ShareAppAdapter

class MainFragmentCHistory : Fragment() {
    private var _binding: FragmentMainCHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainCHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShare.setOnClickListener {
            showShareOptionsBottomSheet()
        }

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> CalendarFragment()
                    1 -> AllHabitsFragment()
                    2 -> ProgressFragment()
                    else -> throw IllegalArgumentException("Unknown position")
                }
            }
        }
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Календарь"
                1 -> "Все привычки"
                2 -> "Достижения"
                else -> null
            }
        }.attach()

    }
    private fun showShareOptionsBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_share_list, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        val recyclerView = bottomSheetView.findViewById<RecyclerView>(R.id.recyclerViewShareApps)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4) // 4 элемента в строке

        // Получаем список приложений, поддерживающих Intent.ACTION_SEND
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
        }
        val resolveInfoList = requireContext().packageManager.queryIntentActivities(shareIntent, 0)

        // Преобразуем ResolveInfo в данные для адаптера
        val shareAppList = resolveInfoList.map { resolveInfo ->
            ShareApp(
                name = resolveInfo.loadLabel(requireContext().packageManager).toString(),
                icon = resolveInfo.loadIcon(requireContext().packageManager),
                packageName = resolveInfo.activityInfo.packageName,
                className = resolveInfo.activityInfo.name
            )
        }

        // Устанавливаем адаптер для RecyclerView
        val adapter = ShareAppAdapter(shareAppList) { selectedApp ->
            shareContentToApp(selectedApp)
            bottomSheetDialog.dismiss()
        }
        recyclerView.adapter = adapter

        // Показываем BottomSheetDialog
        bottomSheetDialog.show()
    }
    private fun shareContentToApp(app: ShareApp) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Текст для отправки через приложение")
            setClassName(app.packageName, app.className)
        }
        startActivity(shareIntent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}