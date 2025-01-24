package com.spase_y.habittracker.data

interface HabitSettings

data class RegularHabit(
    val name: String,
    val icon: Int,
    val iconColor: Int,
    val createdAt: Long,
    val days: List<Days>,
    val repeats: RepeatHabitCount? = null,
    val executeAt: ExecuteTime = ExecuteTime.NOT_IMPORTANT,
    val notifications: HabitNotification? = null,
    val deadline: Long? = null,
    private val type: String = "RegularHabit",
): HabitSettings

data class HarmfulHabit(
    val name: String,
    val icon: Int,
    val iconColor: Int,
    val createdAt: Long,
    val days: Days = Days.EVERYDAY,
    val notifications: HabitNotification? = null,
    val deadline: Long? = null,
    private val type: String = "HarmfulHabit",
): HabitSettings

data class OneTimeHabit(
    val name: String,
    val icon: Int,
    val iconColor: Int,
    val createdAt: Long,
    val executeAt: Long,
    val notifications: HabitNotification? = null,
    val deadline: Long? = null,
    private val type: String = "OneTimeHabit",
): HabitSettings


data class HabitNotification(
    val notificationTime: Long,
    val soundEffect: Int,
    val text: String,
)

data class RepeatHabitCount(
    val count: Int,
    val period: HabitPeriod,
    val countDaysDone: Int = 0
)

enum class HabitPeriod {
    WEEK,
    MONTH,
    YEAR
}

enum class ExecuteTime {
    NOT_IMPORTANT,
    MORNING,
    DAY,
    Evening,
}
