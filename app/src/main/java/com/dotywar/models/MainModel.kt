package com.dotywar.models

import androidx.lifecycle.MutableLiveData
import com.daniel.appbase.BaseModel
import com.daniel.appbase.StatusVO
import com.daniel.appbase.components.SingleEventLiveData
import com.dotywar.R
import com.dotywar.service.FundingAPI
import com.dotywar.vos.DummyVo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MainModel : BaseModel() {

    var mAPI: FundingAPI? = null

    init {
        mAPI = LoginModel.createService(FundingAPI::class.java)
    }

    fun getReports(
        statusLD: SingleEventLiveData<StatusVO>,
        reportsLD: MutableLiveData<List<DummyVo>>
    ) {
        mAPI!!.getReports()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                if (response == null) {
                    statusLD.value = StatusVO(R.string.failed_to_login, StatusVO.TYPE.ERROR)
                } else {
                    reportsLD.value = emptyList()
                }
            }
    }

    fun getNotifications(
        statusLD: SingleEventLiveData<StatusVO>,
        notificationsLD: MutableLiveData<List<DummyVo>>
    ) {
        mAPI!!.getReports()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                if (response == null) {
                    statusLD.value = StatusVO(R.string.failed_to_login, StatusVO.TYPE.ERROR)
                } else {
                    notificationsLD.value = emptyList()
                }
            }
    }

    fun getFundings(
        statusLD: SingleEventLiveData<StatusVO>,
        fundingLD: MutableLiveData<List<DummyVo>>
    ) {
        mAPI!!.getFundings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                if (response == null) {
                    statusLD.value = StatusVO(R.string.failed_to_login, StatusVO.TYPE.ERROR)
                } else {
                    fundingLD.value = emptyList()
                }
            }
    }
}