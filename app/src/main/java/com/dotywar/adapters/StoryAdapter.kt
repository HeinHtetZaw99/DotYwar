package com.dotywar.adapters

import android.content.Context
import android.view.ViewGroup
import com.daniel.appbase.recyclerview.BaseRecyclerAdapter
import com.dotywar.R
import com.dotywar.viewholders.StoryViewHolder
import com.dotywar.vos.DummyVo

class StoryAdapter(context: Context) : BaseRecyclerAdapter<DummyVo, StoryViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(mLayoutInflator.inflate(R.layout.cardview_story, parent, false))
    }

    override fun getItemCount() = 5

}