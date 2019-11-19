package com.naufalprakoso.storybook.ui.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.naufalprakoso.storybook.R
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            return ProfileFragment();
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.tab_layout.setupWithViewPager(view.view_pager)
        view.tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        view.tab_layout.addTab(view.tab_layout.newTab().setText(getString(R.string.title_posts)))
        view.tab_layout.addTab(view.tab_layout.newTab().setText(getString(R.string.title_circle)))

        activity?.supportFragmentManager?.let {
            TabsProfileManager(it, 0)
        }

        val tabsAdapter = activity?.supportFragmentManager?.let {
            TabsProfileManager(it, view.tab_layout.tabCount)
        }
        view.view_pager.adapter = tabsAdapter
        view.view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(view.tab_layout))

        view.tab_layout.setTabTextColors(
            ContextCompat.getColor(view.context, R.color.colorPrimary),
            ContextCompat.getColor(view.context, R.color.colorPrimaryDark)
        )

        view.tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                view.view_pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        return view
    }

}