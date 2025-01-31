package com.spase_y.habittracker.main.navigation_fragment.d.notification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.spase_y.habittracker.MainActivity.Companion.getSharedPreferences
import com.spase_y.habittracker.R
import com.spase_y.habittracker.data.Days
import com.spase_y.habittracker.data.NotificationsManager
import com.spase_y.habittracker.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<View>(R.id.llNavigation)?.visibility = View.GONE



        val totalTime = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.TOTAL).time
        binding.showBottomSheetButton.text = parseLongToTime(totalTime)
        val morningTime = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.MORNING).time
        binding.showBottomSheetButton2.text = parseLongToTime(morningTime)
        val afternoonTime = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.AFTERNOON).time
        binding.showBottomSheetButton3.text = parseLongToTime(afternoonTime)
        val eveningTime = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.EVENING).time
        binding.showBottomSheetButton4.text = parseLongToTime(eveningTime)

        updateDaysUI()

        binding.swTotal.isChecked = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.TOTAL).isEnabled
        binding.swMorning.isChecked = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.MORNING).isEnabled
        binding.swAfternoon.isChecked = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.AFTERNOON).isEnabled
        binding.swEvening.isChecked = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.EVENING).isEnabled


        binding.ivArrowBack1.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.llnotification5.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, SoundEffectFragment())
                .addToBackStack(null)
                .commit()
        }

        setupDayButton(binding.showBottomSheetDay, NotificationsManager.Companion.NotificationVariants.TOTAL)
        setupDayButton(binding.showBottomSheetDay2, NotificationsManager.Companion.NotificationVariants.MORNING)
        setupDayButton(binding.showBottomSheetDay3, NotificationsManager.Companion.NotificationVariants.AFTERNOON)
        setupDayButton(binding.showBottomSheetDay4, NotificationsManager.Companion.NotificationVariants.EVENING)

        setupButton(binding.showBottomSheetButton, 0, 23, NotificationsManager.Companion.NotificationVariants.TOTAL) // Полный день
        setupButton(binding.showBottomSheetButton2, 8, 14, NotificationsManager.Companion.NotificationVariants.MORNING) // Утро
        setupButton(binding.showBottomSheetButton3, 14, 19, NotificationsManager.Companion.NotificationVariants.AFTERNOON) // День
        setupButton(binding.showBottomSheetButton4, 19, 23, NotificationsManager.Companion.NotificationVariants.EVENING) // Вечер

        setupSwitch(binding.swTotal, NotificationsManager.Companion.NotificationVariants.TOTAL) // Полный день
        setupSwitch(binding.swMorning, NotificationsManager.Companion.NotificationVariants.MORNING) // Утро
        setupSwitch(binding.swAfternoon, NotificationsManager.Companion.NotificationVariants.AFTERNOON) // День
        setupSwitch(binding.swEvening, NotificationsManager.Companion.NotificationVariants.EVENING) // Вечер
    }

    private fun setupSwitch(
        switch: Switch,
        variant: NotificationsManager.Companion.NotificationVariants
    ) {
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            currentVariant = variant

            val days = notificationsManager.getNotificationTime(currentVariant!!).days
            val time = notificationsManager.getNotificationTime(currentVariant!!).time
            val settings = NotificationsManager.Companion.NotificationSettings(
                time,
                days,
                isChecked
            )
            notificationsManager.saveNotificationTime(currentVariant!!, settings)
        }
    }

    private fun parseListDaysToString(totalDays: List<Days>): String {
        var str = ""
        totalDays.forEach {
            str += it.displayName + ", "
        }

        return str.dropLast(2)
    }

    private fun setupButton(button: Button, minHour: Int, maxHour: Int, variant: NotificationsManager.Companion.NotificationVariants) {
        button.setOnClickListener {
            currentVariant = variant
            val bottomSheet = MyBottomSheetTimeFragment.newInstance(minHour, maxHour).apply {
                setCallback { selectedTime ->
                    button.text = selectedTime // Обновляем текст кнопки

                    if (currentVariant == null) return@setCallback
                    val days = notificationsManager.getNotificationTime(currentVariant!!).days
                    val isEnabled = notificationsManager.getNotificationTime(currentVariant!!).isEnabled
                    val time = parseTime(selectedTime)
                    val settings = NotificationsManager.Companion.NotificationSettings(
                        time,
                        days,
                        isEnabled
                    )
                    notificationsManager.saveNotificationTime(currentVariant!!, settings)
                }
            }
            bottomSheet.show(parentFragmentManager, "MyBottomSheet")
        }
    }

    val notificationsManager by lazy {
        NotificationsManager(getSharedPreferences())
    }
    var currentVariant: NotificationsManager.Companion.NotificationVariants?  = null
    private fun setupDayButton(button: Button, variant: NotificationsManager.Companion.NotificationVariants) {
        button.setOnClickListener {
            currentVariant = variant
            val savedDays = notificationsManager.getNotificationTime(currentVariant!!).days
            val bottomDaySheet = MyBottomSheetDayFragment(savedDays.toMutableList()) { days ->
                if (currentVariant == null) return@MyBottomSheetDayFragment
                val time = notificationsManager.getNotificationTime(currentVariant!!).time
                val isEnabled = notificationsManager.getNotificationTime(currentVariant!!).isEnabled
                val settings = NotificationsManager.Companion.NotificationSettings(
                    time,
                    days,
                    isEnabled
                )
                notificationsManager.saveNotificationTime(currentVariant!!, settings)

                updateDaysUI()
            }
            bottomDaySheet.show(parentFragmentManager, "MyBottomSheet")
        }
    }

    private fun updateDaysUI() {
        val totalDays = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.TOTAL).days
        binding.showBottomSheetDay.text = parseListDaysToString(totalDays)
        val morningDays = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.MORNING).days
        binding.showBottomSheetDay2.text = parseListDaysToString(morningDays)
        val afternoonDays = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.AFTERNOON).days
        binding.showBottomSheetDay3.text = parseListDaysToString(afternoonDays)
        val eveningDays = notificationsManager.getNotificationTime(NotificationsManager.Companion.NotificationVariants.EVENING).days
        binding.showBottomSheetDay4.text = parseListDaysToString(eveningDays)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun parseLongToTime(milliseconds: Long): String {
        if (milliseconds < 0 || milliseconds >= 24 * 60 * 60 * 1000) {
            throw IllegalArgumentException("Milliseconds must be between 0 and 86399999")
        }

        val totalSeconds = milliseconds / 1000
        val hours = (totalSeconds / 3600).toInt()
        val minutes = ((totalSeconds % 3600) / 60).toInt()

        return String.format("%02d:%02d", hours, minutes)
    }

    fun parseTime(timeString: String): Long {
        val parts = timeString.split(":")
        if (parts.size != 2) throw IllegalArgumentException("Invalid time format: $timeString")

        val hours = parts[0].toIntOrNull() ?: throw IllegalArgumentException("Invalid hour value: ${parts[0]}")
        val minutes = parts[1].toIntOrNull() ?: throw IllegalArgumentException("Invalid minute value: ${parts[1]}")

        if (hours !in 0..23) throw IllegalArgumentException("Hour must be between 0 and 23")
        if (minutes !in 0..59) throw IllegalArgumentException("Minute must be between 0 and 59")

        val totalMilliseconds = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000)
        return totalMilliseconds.toLong()
    }

}



