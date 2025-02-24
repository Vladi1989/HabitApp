package com.spase_y.habittracker.main.navigation_fragment.d.language_parameters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentLanguageParametersBinding
import java.util.Locale
import android.content.res.Configuration

class LanguageParametersFragment : Fragment() {
    private var _binding: FragmentLanguageParametersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanguageParametersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<View>(R.id.llNavigation)?.visibility = View.GONE

        binding.ivArrowBack1.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.linearLayout10.setOnClickListener {
            setLocale("ru")
            binding.ivCheck1.visibility = View.VISIBLE
            binding.ivCheck2.visibility = View.GONE
        }
        binding.linearLayout11.setOnClickListener {
            setLocale("en")
            binding.ivCheck1.visibility = View.GONE
            binding.ivCheck2.visibility = View.VISIBLE
        }
    }
    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale
        requireActivity().resources.updateConfiguration(config, requireActivity().resources.displayMetrics)

        // Обновляем активность, чтобы изменения локализации вступили в силу
        requireActivity().recreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}