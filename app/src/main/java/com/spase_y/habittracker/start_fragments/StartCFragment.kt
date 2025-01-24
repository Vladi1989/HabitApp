package com.spase_y.habittracker.start_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentStartCBinding


class StartCFragment : Fragment() {
    private var _binding: FragmentStartCBinding? = null
    private val binding get() = _binding!!

    private var isCard1Selected = true
    private var isCard2Selected = false
    private var isCard3Selected = false
    private var isCard4Selected = false
    private var isCard5Selected = false
    private var isCard6Selected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartCBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivArrowBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.btnGoToStartD.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, StartDFragment())
                .addToBackStack(null)
                .commit()
        }

        // Устанавливаем начальные состояния для всех карточек
        setInitialCardStates()

        // Слушатели для кликов по карточкам
        binding.cardView1.setOnClickListener {
            isCard1Selected = toggleCardState(binding.cardView1, binding.ivCheckMark1, isCard1Selected)
        }
        binding.cardView2.setOnClickListener {
            isCard2Selected = toggleCardState(binding.cardView2, binding.ivCheckMark2, isCard2Selected)
        }
        binding.cardView3.setOnClickListener {
            isCard3Selected = toggleCardState(binding.cardView3, binding.ivCheckMark3, isCard3Selected)
        }
        binding.cardView4.setOnClickListener {
            isCard4Selected = toggleCardState(binding.cardView4, binding.ivCheckMark4, isCard4Selected)
        }
        binding.cardView5.setOnClickListener {
            isCard5Selected = toggleCardState(binding.cardView5, binding.ivCheckMark5, isCard5Selected)
        }
        binding.cardView6.setOnClickListener {
            isCard6Selected = toggleCardState(binding.cardView6, binding.ivCheckMark6, isCard6Selected)
        }
    }

    fun setInitialCardStates() {
        // Устанавливаем начальные состояния для всех карточек
        isCard1Selected = toggleCardState(binding.cardView1, binding.ivCheckMark1, false)
        isCard2Selected = toggleCardState(binding.cardView2, binding.ivCheckMark2, true)
        isCard3Selected = toggleCardState(binding.cardView3, binding.ivCheckMark3, true)
        isCard4Selected = toggleCardState(binding.cardView4, binding.ivCheckMark4, true)
        isCard5Selected = toggleCardState(binding.cardView5, binding.ivCheckMark5, true)
        isCard6Selected = toggleCardState(binding.cardView6, binding.ivCheckMark6, true)
    }
    fun toggleCardState(cardView: CardView, checkMark: ImageView, isSelected: Boolean): Boolean {
        if (isSelected) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.card_view
            ))
            checkMark.visibility = View.GONE
        } else {
            cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.primary
            ))
            checkMark.visibility = View.VISIBLE
        }
        return !isSelected
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}