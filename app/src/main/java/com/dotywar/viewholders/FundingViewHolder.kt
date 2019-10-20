package com.dotywar.viewholders

import android.view.View
import com.daniel.appbase.recyclerview.BaseViewHolder
import com.dotywar.delegates.FundingDelegate
import com.dotywar.vos.DummyVo

class FundingViewHolder(private val view: View, private val fundingDelegate: FundingDelegate) :
    BaseViewHolder<DummyVo>(view) {
    override fun setData(mData: DummyVo) {

    }

    override fun onClick(v: View) {
        super.onClick(v)
        fundingDelegate.goToFundingDetails()
    }

}