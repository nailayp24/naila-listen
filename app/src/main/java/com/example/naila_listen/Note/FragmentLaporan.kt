package com.example.naila_listen.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.naila_listen.data.AppDatabase
import com.example.naila_listen.databinding.FragmentLaporanBinding
import kotlinx.coroutines.launch

class FragmentLaporan : Fragment() {

    private var _binding: FragmentLaporanBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireContext())

        fetchLaporan()

        binding.fabAddLaporan.setOnClickListener {
            startActivity(Intent(requireContext(), LaporanFormActivity::class.java))
        }
    }

    private fun fetchLaporan() {
        viewLifecycleOwner.lifecycleScope.launch {
            val dataLaporan = db.laporanDao().getAllLaporan()

            if (dataLaporan.isEmpty()) {
                binding.layoutEmptyLaporan.visibility = View.VISIBLE
                binding.rvLaporan.visibility = View.GONE
            } else {
                binding.layoutEmptyLaporan.visibility = View.GONE
                binding.rvLaporan.visibility = View.VISIBLE

                binding.rvLaporan.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
                binding.rvLaporan.adapter = LaporanAdapter(dataLaporan)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchLaporan()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}