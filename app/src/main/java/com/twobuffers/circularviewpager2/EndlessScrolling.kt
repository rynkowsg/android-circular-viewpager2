package com.twobuffers.circularviewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EndlessScrollAdapter constructor(
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    private val items: List<Fragment>
) : FragmentStateAdapter(fm, lifeCycle) {

    override fun createFragment(position: Int): Fragment = items[position.rem(items.size)]

    override fun getItemCount(): Int = if (items.isEmpty()) 0 else Int.MAX_VALUE

    // This override is crucial so the adapter can handle using same fragments over and over again.
    override fun getItemId(position: Int): Long = position.rem(items.size).toLong()

    fun getCenterPage(position: Int = 0): Int {
        val middle = Integer.MAX_VALUE / 2
        return middle - position.rem(items.size)  + 1 + position
    }
}

fun ViewPager2.setupEndlessScrollAdapter(
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    list: List<Fragment>
): FragmentStateAdapter {
    val endlessAdapter = EndlessScrollAdapter(fm, lifeCycle, list)
    adapter = endlessAdapter
    setCurrentItem(endlessAdapter.getCenterPage(), false)
    return endlessAdapter
}

inline fun ViewPager2.setupEndlessScrollAdapter(
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    block: () -> List<Fragment>
) = setupEndlessScrollAdapter(fm, lifeCycle, block())

fun ViewPager2.setupAutomaticScroll(lifeCycle: Lifecycle, delayMillis: Long = 10_000) {
    lifeCycle.coroutineScope.launch {
        repeat(Int.MAX_VALUE) {
            delay(delayMillis)
            currentItem += 1
        }
    }
}
