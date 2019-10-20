package com.dotywar.viewmodels

import com.daniel.appbase.BaseViewModel
import com.daniel.appbase.StatusVO
import com.daniel.appbase.components.SingleEventLiveData
import com.dotywar.models.LoginModel

class LoginViewModel : BaseViewModel() {

    var statusLD = SingleEventLiveData<StatusVO>()

    override fun init() {

    }

    fun login(phoneNumber: String) {
        LoginModel.login(phoneNumber, statusLD)
    }


}