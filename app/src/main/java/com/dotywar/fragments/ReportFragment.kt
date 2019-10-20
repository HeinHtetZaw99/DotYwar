package com.dotywar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniel.appbase.BaseFragments
import com.dotywar.MainActivity
import com.dotywar.R
import com.dotywar.adapters.ReportAdapter
import com.dotywar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.layout_fragment_funding.view.*
import kotlinx.android.synthetic.main.layout_fragment_report.view.*

class ReportFragment : BaseFragments() {
    private var viewModel: MainViewModel? = null
    private lateinit var mAdapter: ReportAdapter
    override fun onError() {

    }

    override fun loadData() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_fragment_report, container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).getViewModel()

        mAdapter = ReportAdapter(context!!)
        view.reportsRv.layoutManager = LinearLayoutManager(context)
        view.fundingsRv.adapter = mAdapter
        viewModel!!.reportLD.observe(viewLifecycleOwner, Observer {
            mAdapter.appendNewData(it)
        })

    }

}