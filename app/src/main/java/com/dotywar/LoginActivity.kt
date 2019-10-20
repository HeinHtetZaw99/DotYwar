package com.dotywar

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daniel.appbase.BaseActivity
import com.daniel.appbase.StatusVO
import com.daniel.appbase.components.PermissionUtil
import com.daniel.appbase.goToAppSettings
import com.daniel.appbase.showLogD
import com.dotywar.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<LoginViewModel>(), PermissionUtil.PermissionAskListener {
    override fun onPermissionPreviouslyDenied() {
        goToAppSettings(this)
    }

    override fun onPermissionDisabled() {
        goToAppSettings(this)
    }

    override fun onPermissionGranted() {
        viewModel!!.login(getPhoneNumbers()!!)
    }

    override fun onNeedPermission() {
    }

    override fun onCreateViewModel(): LoginViewModel {
        return ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun loadData() {
    }

    override fun onError() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        registerBtn.setOnClickListener {
            redirectToRegister()
        }
        loginBtn.setOnClickListener {
            loginWithPhoneNumber()
        }

        viewModel!!.statusLD.observe(this, Observer {
            if (it.errorType == StatusVO.TYPE.ERROR) {

            } else if (it.errorType == StatusVO.TYPE.POSITIVE) {
                redirectToMainActivity()

            }
        })
    }

    private fun redirectToMainActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }

    private fun loginWithPhoneNumber() {
        PermissionUtil.checkPermission(this, Manifest.permission.READ_PHONE_STATE, this)
    }

    private fun redirectToRegister() {
        startActivity(RegisterActivity.newIntent(this))
    }


    @SuppressLint("MissingPermission")
    fun getPhoneNumbers(): String? {
        val tMgr = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val mPhoneNumberOne = tMgr.line1Number
        showLogD("Phone number from user $mPhoneNumberOne")
        return mPhoneNumberOne
    }
}
