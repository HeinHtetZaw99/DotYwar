package com.dotywar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dotywar.adapters.StoryAdapter
import com.dotywar.adapters.TransactionAdapter
import kotlinx.android.synthetic.main.activity_fund_details.*
import kotlinx.android.synthetic.main.content_fund_details.*

class FundDetailsActivity : AppCompatActivity() {

    private lateinit var storyAdapter: StoryAdapter
    private lateinit var transactionAdapter: TransactionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_details)
        setSupportActionBar(toolbar)

        storyAdapter = StoryAdapter(context = baseContext)
        storyRv.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL, false)
        storyRv.adapter = storyAdapter

        transactionAdapter = TransactionAdapter(context = baseContext)
        donorRv.layoutManager = LinearLayoutManager(baseContext)
        donorRv.adapter = storyAdapter


    }

    companion object {
        fun newIntent(context: Context) = Intent(context, FundDetailsActivity::class.java)
    }

}
