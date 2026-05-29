package com.example.naila_listen.Home.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingTabsAdapter(
    activity: FragmentActivity,
    private val pageCount: Int
) : FragmentStateAdapter(activity) {

    // Memberitahu ViewPager2 total jumlah halaman secara aman
    override fun getItemCount(): Int = pageCount

    // FIX AMAN: Fragment baru akan dilahirkan secara dinamis saat user menggeser layar
    override fun createFragment(position: Int): Fragment {
        return OnboardingPageFragment.newInstance(position)
    }
}