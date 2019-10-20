package com.dotywar.models

import android.annotation.SuppressLint
import com.daniel.appbase.BaseModel
import com.daniel.appbase.StatusVO
import com.daniel.appbase.components.SharePrefUtils
import com.daniel.appbase.components.SingleEventLiveData
import com.dotywar.R
import com.dotywar.mapper.UserMapper
import com.dotywar.service.LoginAPI
import com.dotywar.vos.UserVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object LoginModel : BaseModel() {

    var mAPI: LoginAPI? = null

    init {
        mAPI = createService(LoginAPI::class.java)
    }

    @SuppressLint("CheckResult")
    fun login(phoneNumber: String, statusLD: SingleEventLiveData<StatusVO>) {
        mAPI!!.login(phoneNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                if (response == null) {
                    statusLD.value = StatusVO(R.string.failed_to_login, StatusVO.TYPE.ERROR)
                } else {
                    SharePrefUtils.instance.save(SharePrefUtils.KEYS.TOKEN,response.accessToken!!)
                    SharePrefUtils.instance.save(SharePrefUtils.KEYB.HAS_LOGIN,true)
                    saveUserData(UserMapper().map(response))
                    statusLD.value = StatusVO("", StatusVO.TYPE.POSITIVE)
                }
            }
    }


    private fun saveUserData(userData: UserVO) {
        SharePrefUtils.instance.save(SharePrefUtils.KEYS.USER_NAME, userData.userName!!)
        SharePrefUtils.instance.save(SharePrefUtils.KEYS.PHONE_NUMBER, userData.phoneNumber!!)
        SharePrefUtils.instance.save(SharePrefUtils.KEYS.ADDRESS, userData.address!!)
        SharePrefUtils.instance.save(SharePrefUtils.KEYS.PROFESSION, userData.profession!!)
    }

    fun logOut(){
        SharePrefUtils.instance.reset(SharePrefUtils.KEYS.TOKEN)
        SharePrefUtils.instance.reset(SharePrefUtils.KEYB.HAS_LOGIN)
    }

}