package com.dotywar.viewmodels

import androidx.lifecycle.MutableLiveData
import com.daniel.appbase.BaseViewModel
import com.daniel.appbase.StatusVO
import com.daniel.appbase.components.SingleEventLiveData
import com.dotywar.models.MainModel
import com.dotywar.vos.DummyVo

class MainViewModel : BaseViewModel() {

    val reportLD = MutableLiveData<List<DummyVo>>()
    val notificationLD = MutableLiveData<List<DummyVo>>()
    val fundingsLD = MutableLiveData<List<DummyVo>>()
    val statusLD = SingleEventLiveData<StatusVO>()
    override fun init() {

    }

    fun getFundings() = MainModel.getFundings(statusLD, fundingsLD)

    fun getNotifications() = MainModel.getFundings(statusLD, notificationLD)

    fun getReports() = MainModel.getFundings(statusLD, reportLD)


}