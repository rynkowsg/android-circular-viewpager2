package com.twobuffers.circularviewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class EndlessScrollAdapter constructor(
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    private val items: List<Fragment>
) : FragmentStateAdapter(fm, lifeCycle) {

    override fun createFragment(position: Int): Fragment = items[position % items.size]

    override fun getItemCount(): Int = if (items.isNotEmpty()) Int.MAX_VALUE else 0

    // This override is crucial so, the adapter can handle using same fragments over and over again.
    override fun getItemId(position: Int): Long = (position % items.size).toLong()

    fun getCenterPage(position: Int = 0): Int {
        val middle = Integer.MAX_VALUE / 2
        return middle - middle % items.size + position
    }
}

fun ViewPager2.setupEndlessScrollAdapter(
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    list: List<Fragment>
) {
    val endlessAdapter = EndlessScrollAdapter(fm, lifeCycle, list)
    adapter = endlessAdapter
    setCurrentItem(endlessAdapter.getCenterPage(), false)
}
