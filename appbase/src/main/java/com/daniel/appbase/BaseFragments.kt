package com.daniel.appbase

import androidx.fragment.app.Fragment
import com.daniel.appbase.components.Connectivity


abstract class BaseFragments : Fragment() {



    internal fun fetchDataFromNetwork() {
        if (Connectivity.isConnected(context!!)) {
            loadData()
        } else
            onError()
    }


    abstract fun loadData()

    abstract fun onError()


}