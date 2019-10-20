package com.dotywar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniel.appbase.BaseFragments
import com.dotywar.MainActivity
import com.dotywar.R
import com.dotywar.viewmodels.MainViewModel

class ProfileFragment : BaseFragments() {
    private var viewModel: MainViewModel? = null
    override fun onError() {

    }

    override fun loadData() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_fragment_profile, container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).getViewModel()

    }

}