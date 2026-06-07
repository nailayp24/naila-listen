package com.example.naila_listen.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.naila_listen.data.AppDatabase
import com.example.naila_listen.databinding.FragmentLogistikBinding
import kotlinx.coroutines.launch

class FragmentLogistik : Fragment() {

    private var _binding: FragmentLogistikBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogistikBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireContext())

        fetchLogistik()

        // 🌟 AKSI KLIK TOMBOL FAB TAMBAH LOGISTIK MURNI KE LOGISTIK FORM 🌟
        binding.fabAddLogistik.setOnClickListener {
            val intent = Intent(requireContext(), LogistikFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchLogistik() {
        viewLifecycleOwner.lifecycleScope.launch {
            val dataLogistik = db.logistikDao().getAllLogistik()

            if (dataLogistik.isEmpty()) {
                binding.layoutEmptyLogistik.visibility = View.VISIBLE
                binding.rvLogistik.visibility = View.GONE
            } else {
                binding.layoutEmptyLogistik.visibility = View.GONE
                binding.rvLogistik.visibility = View.VISIBLE

                // 🌟 ATUR RECYCLERVIEW AGAR MENGGAMBAR DATA ASLI 🌟
                binding.rvLogistik.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
                binding.rvLogistik.adapter = LogistikAdapter(dataLogistik)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchLogistik()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}