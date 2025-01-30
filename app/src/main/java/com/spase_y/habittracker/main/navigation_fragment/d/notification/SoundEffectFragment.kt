package com.spase_y.habittracker.main.navigation_fragment.d.notification

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentSoundEffectBinding

class SoundEffectFragment : Fragment() {
    private var _binding: FragmentSoundEffectBinding? = null
    private val binding get() = _binding!!
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoundEffectBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivArrowBack1.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.linearLayout10.setOnClickListener {playSound(R.raw.sound_1)
            binding.ivTrackOn1.visibility = View.VISIBLE
            binding.ivTrackOn2.visibility = View.GONE
            binding.ivTrackOn3.visibility = View.GONE
            binding.ivTrackOn4.visibility = View.GONE}

        binding.linearLayout11.setOnClickListener { playSound(R.raw.sound_2)
            binding.ivTrackOn1.visibility = View.GONE
            binding.ivTrackOn2.visibility = View.VISIBLE
            binding.ivTrackOn3.visibility = View.GONE
            binding.ivTrackOn4.visibility = View.GONE}
        binding.linearLayout12.setOnClickListener { playSound(R.raw.sound_3)
            binding.ivTrackOn1.visibility = View.GONE
            binding.ivTrackOn2.visibility = View.GONE
            binding.ivTrackOn3.visibility = View.VISIBLE
            binding.ivTrackOn4.visibility = View.GONE
        }
        binding.linearLayout13.setOnClickListener { playSound(R.raw.sound_4)
            binding.ivTrackOn1.visibility = View.GONE
            binding.ivTrackOn2.visibility = View.GONE
            binding.ivTrackOn3.visibility = View.GONE
            binding.ivTrackOn4.visibility = View.VISIBLE}

    }
    private fun playSound(soundResId: Int) {
        // Освобождаем предыдущий MediaPlayer, если он играет
        mediaPlayer?.release()

        // Создаем новый MediaPlayer с переданным звуком
        mediaPlayer = MediaPlayer.create(requireContext(), soundResId)
        mediaPlayer?.start()

        // Освобождаем ресурсы после завершения воспроизведения
        mediaPlayer?.setOnCompletionListener {
            it.release()
            mediaPlayer = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}