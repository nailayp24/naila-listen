package com.example.naila_listen.Home.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.naila_listen.databinding.FragmentTutorialPageBinding

class OnboardingPageFragment : Fragment() {
    private var _binding: FragmentTutorialPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorialPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt("position") ?: 0

        // Mengubah teks slide onboarding agar berfokus pada sistem mitigasi bencana
        when(position) {
            0 -> {
                binding.tvTitleTutorial.text = "Sistem Informasi SIGANA"
                binding.tvDescTutorial.text = "Platform terintegrasi untuk pemantauan dini, pelaporan cepat, dan tanggap darurat bencana alam secara realtime."
            }
            1 -> {
                binding.tvTitleTutorial.text = "Manajemen Logistik Darurat"
                binding.tvDescTutorial.text = "Pantau dan distribusikan bantuan sembako, pasokan obat-obatan, serta kebutuhan medis ke wilayah terdampak dengan transparan."
            }
            2 -> {
                binding.tvTitleTutorial.text = "Mobilisasi Relawan & Posko"
                binding.tvDescTutorial.text = "Koordinasikan pergerakan tim penyelamat, pemetaan titik posko pengungsian, dan penyaluran donasi langsung ke lapangan."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(position: Int): OnboardingPageFragment {
            val fragment = OnboardingPageFragment()
            val args = Bundle()
            args.putInt("position", position)
            fragment.arguments = args
            return fragment
        }
    }
}