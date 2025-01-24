package com.spase_y.habittracker.data

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HabitsManger(val sharedPreferences: SharedPreferences) {
    private val gson = Gson()
    fun addHabit(habit: HabitSettings) {
        val habits = getAllHabits().toMutableList()
        habits.add(habit)
        val data = gson.toJson(habits)
        sharedPreferences.edit().putString(FULL_HABITS_LIST_TAG, data).apply()
    }
    fun getAllHabits(): List<HabitSettings> {
        val _habitsStr = sharedPreferences.getString(FULL_HABITS_LIST_TAG, "null")
        if (_habitsStr == "null") {
            return emptyList()
        }

        val habitsListStr = splitJsonObjects(_habitsStr!!)
        val habitsList = mutableListOf<HabitSettings>()
        habitsListStr.forEach {
            if (it.contains("\"type\":\"RegularHabit\"")) {
                val item = Gson().fromJson<RegularHabit>(it, RegularHabit::class.java)
                habitsList.add(item)
            } else if (it.contains("\"type\":\"OneTimeHabit\"")) {
                val item = Gson().fromJson<OneTimeHabit>(it, OneTimeHabit::class.java)
                habitsList.add(item)
            } else if (it.contains("\"type\":\"HarmfulHabit\"")) {
                val item = Gson().fromJson<HarmfulHabit>(it, HarmfulHabit::class.java)
                habitsList.add(item)
            }
        }
        return habitsList
    }

    fun splitJsonObjects(jsonString: String): List<String> {
        val regex = """\{.*?\}""".toRegex()
        return regex.findAll(jsonString).map { it.value }.toList()
    }

    fun deleteHabit(habit: HabitSettings) {
        val habits = getAllHabits().toMutableList()
        habits.remove(habit)
        val data = gson.toJson(habits)
        sharedPreferences.edit().putString(FULL_HABITS_LIST_TAG, data).apply()
    }

    private companion object {
        val FULL_HABITS_LIST_TAG = "FULL_HABITS_LIST_TAG"
    }
}

