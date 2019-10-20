package com.daniel.appbase

import androidx.lifecycle.ViewModel
import com.daniel.appbase.components.SingleEventLiveData


abstract class BaseViewModel : ViewModel() {

    val fbLogOutStatus: SingleEventLiveData<Boolean> = SingleEventLiveData()


    internal abstract fun shutDownModel()

    abstract fun init()


}
