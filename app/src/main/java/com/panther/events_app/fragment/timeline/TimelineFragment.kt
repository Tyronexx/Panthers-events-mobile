package com.panther.events_app.fragment.timeline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.panther.events_app.R
import com.panther.events_app.adapter.ViewPagerAdapter
import com.panther.events_app.databinding.FragmentTimelineBinding

class TimelineFragment : Fragment() {

    private var _binding : FragmentTimelineBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimelineBinding.inflate(inflater, container, false)

        viewPager = binding.pager
        tabLayout = binding.tabLayout

        val fragmentList = listOf(
            TimelineEveryoneFragment(),
            TimelineFriendsFragment()
        )
        val adapter = ViewPagerAdapter(this, fragmentList)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

            when (position){
                0 -> {
                    tab.text = "Everyone"
                }
                1 -> {
                    tab.text = "Friends"
                }
            }
        }.attach()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}