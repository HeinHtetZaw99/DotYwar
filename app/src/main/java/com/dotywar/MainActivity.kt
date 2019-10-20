package com.dotywar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.daniel.appbase.BaseActivity
import com.dotywar.fragments.FundFragment
import com.dotywar.fragments.ProfileFragment
import com.dotywar.fragments.ReportFragment
import com.dotywar.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {
    override fun onCreateViewModel() = ViewModelProviders.of(this)[MainViewModel::class.java]


    override fun loadData() {

    }

    override fun onError() {

    }

    private var fm = supportFragmentManager
    private var activeFragment: Fragment? = null
    private val fundingFragment = FundFragment()
    private val reportFragment = ReportFragment()
    private val accountFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activeFragment = fundingFragment

        bottomNavigationBar
            .setOnNavigationItemSelectedListener bottomNavigationView@{ menuItem ->
                when (menuItem.itemId) {
                    R.id.funding -> {
                        fm.beginTransaction().hide(activeFragment!!).show(fundingFragment).commit()
                        activeFragment = fundingFragment
                    }
                    R.id.report -> {
                        fm.beginTransaction().hide(activeFragment!!).show(reportFragment).commit()
                        activeFragment = reportFragment

                    }
                    R.id.account -> {
                        fm.beginTransaction().hide(activeFragment!!).show(accountFragment).commit()
                        activeFragment = accountFragment
                    }
                }
                false
            }


    }

    fun getViewModel() = viewModel!!

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}
