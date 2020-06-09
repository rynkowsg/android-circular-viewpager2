package com.twobuffers.circularviewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.twobuffers.circularviewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewPager.setupEndlessScrollAdapter(this) {
            listOf(Fragment1(), Fragment2(), Fragment3(), Fragment4())
        }
        binding.viewPager.setupAutomaticScroll(lifecycle)
        // When uncommented, the viewpager can't be swiped by user.
//        binding.viewPager.isUserInputEnabled = false
    }
}
