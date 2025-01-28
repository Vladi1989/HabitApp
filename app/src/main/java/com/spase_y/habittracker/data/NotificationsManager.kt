package com.spase_y.habittracker.data

import android.content.SharedPreferences
import com.google.gson.Gson

class NotificationsManager(private val sharedPreferences: SharedPreferences) {
    private val gson = Gson()

    fun saveNotificationTime(variant: NotificationVariants, settings: NotificationSettings) {
        var finishSettings = settings
        if (settings.days.size == 7) {
            finishSettings = settings.copy(days = listOf(Days.EVERYDAY))
        }
        sharedPreferences.edit().putString("NOTIFICATION_DATA_${variant.name}_TAG", gson.toJson(finishSettings)).apply()
    }
    fun getNotificationTime(variant: NotificationVariants): NotificationSettings {
        val _data = sharedPreferences.getString("NOTIFICATION_DATA_${variant.name}_TAG", "null")
        if (_data == "null") {
            when(variant) {
                NotificationVariants.TOTAL -> return defaultTotalNotification
                NotificationVariants.MORNING -> return defaultMorningNotification
                NotificationVariants.AFTERNOON -> return defaultAfternoonNotification
                NotificationVariants.EVENING -> return defaultEveningNotification
            }
        }
        val data = gson.fromJson<NotificationSettings>(_data, NotificationSettings::class.java)
        return data
    }

    companion object {
        enum class NotificationVariants {
            TOTAL,
            MORNING,
            AFTERNOON,
            EVENING,
        }

        data class NotificationSettings(
            val time: Long,
            val days: List<Days>,
            val isEnabled: Boolean
        )
    }

    private val defaultTotalNotification = NotificationSettings(
        (12 * 60 * 60 * 1000).toLong(),
        listOf(Days.EVERYDAY),
        true
    )
    private val defaultMorningNotification = NotificationSettings(
        (8 * 60 * 60 * 1000).toLong(),
        listOf(Days.EVERYDAY),
        true
    )
    private val defaultAfternoonNotification = NotificationSettings(
        (14 * 60 * 60 * 1000).toLong(),
        listOf(Days.EVERYDAY),
        true
    )
    private val defaultEveningNotification = NotificationSettings(
        (19 * 60 * 60 * 1000).toLong(),
        listOf(Days.EVERYDAY),
        true
    )
}