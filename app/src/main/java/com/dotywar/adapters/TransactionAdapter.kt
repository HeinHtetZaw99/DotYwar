package com.dotywar.adapters

import android.content.Context
import android.view.ViewGroup
import com.daniel.appbase.recyclerview.BaseRecyclerAdapter
import com.dotywar.R
import com.dotywar.viewholders.TransactionViewHolder
import com.dotywar.vos.DummyVo

class TransactionAdapter(context: Context) :
    BaseRecyclerAdapter<DummyVo, TransactionViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            mLayoutInflator.inflate(
                R.layout.cardview_fund_transaction,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 5

}