package com.twobuffers.circularviewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import timber.log.Timber

class Fragment1 : Fragment() {

    init {
        Timber.d("init")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("onCreateView(), $this, ${this.id}, ${this.hashCode()}")
        return inflater.inflate(R.layout.fragment_1, container, false)
    }
}
