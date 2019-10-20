package com.dotywar.adapters

import android.content.Context
import android.view.ViewGroup
import com.daniel.appbase.recyclerview.BaseRecyclerAdapter
import com.dotywar.R
import com.dotywar.viewholders.ReportViewHolder
import com.dotywar.vos.DummyVo

class ReportAdapter(context: Context) : BaseRecyclerAdapter<DummyVo, ReportViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(mLayoutInflator.inflate(R.layout.cardview_report, parent, false))
    }

    override fun getItemCount() = 5

}