package com.spase_y.habittracker

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.spase_y.habittracker.databinding.ActivityMainBinding
import com.spase_y.habittracker.main.MainAppFragment
import com.spase_y.habittracker.start_fragments.LoadingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.login_ui_bg_color)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, MainAppFragment())
            .commit()
    }

    companion object {
        fun Fragment.getSharedPreferences(): SharedPreferences {
            return requireActivity().getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        }
    }
}