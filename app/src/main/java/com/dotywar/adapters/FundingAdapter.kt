package com.dotywar.adapters

import android.content.Context
import android.view.ViewGroup
import com.daniel.appbase.recyclerview.BaseRecyclerAdapter
import com.dotywar.R
import com.dotywar.delegates.FundingDelegate
import com.dotywar.viewholders.FundingViewHolder
import com.dotywar.vos.DummyVo

class FundingAdapter(context: Context,private val fundingDelegate: FundingDelegate ) : BaseRecyclerAdapter<DummyVo, FundingViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundingViewHolder {
        return FundingViewHolder(mLayoutInflator.inflate(R.layout.cardview_fund, parent, false),fundingDelegate)
    }

    override fun getItemCount() = 5

}