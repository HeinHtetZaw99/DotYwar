package com.dotywar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniel.appbase.BaseFragments
import com.dotywar.FundDetailsActivity
import com.dotywar.MainActivity
import com.dotywar.R
import com.dotywar.adapters.FundingAdapter
import com.dotywar.delegates.FundingDelegate
import com.dotywar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.layout_fragment_funding.view.*


class FundFragment : BaseFragments(), FundingDelegate {
    override fun goToFundingDetails() {
        startActivity(FundDetailsActivity.newIntent(context!!))
    }

    private var viewModel: MainViewModel? = null
    private lateinit var mAdapter: FundingAdapter
    override fun onError() {

    }

    override fun loadData() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragment_funding, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).getViewModel()

        mAdapter = FundingAdapter(context!!, this)
        view.fundingsRv.layoutManager = LinearLayoutManager(context)
        view.fundingsRv.adapter = mAdapter
        viewModel!!.fundingsLD.observe(viewLifecycleOwner, Observer {
            mAdapter.appendNewData(it)
        })

    }

}