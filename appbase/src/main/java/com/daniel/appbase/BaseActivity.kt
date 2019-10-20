package com.daniel.appbase

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import java.io.IOException

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(){
    var viewModel : T? = null
    /**
     * For Creating ViewModel
     */
    protected abstract fun onCreateViewModel(): T

    /**
     * This method is for handling different Impl of activities in cases
     * When user tries to refresh data from server and there was no error
     */
    abstract fun loadData()

    /**
     * This method is for handling different Impl of activities in cases
     * When user tries to refresh data from server and NO_INTERNET_ERROR came up
     */
    abstract fun onError()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = onCreateViewModel()
        viewModel!!.init()
    }

    fun <T> loadDataListFromJson(context: Context, fileName : String): List<T> {
        var dataList: List<T> = emptyList()
        try {
            val inStream = context.assets.open(fileName)
            val size = inStream.available()
            val buffer = ByteArray(size)
            inStream.read(buffer)
            inStream.close()
            val dataString = String()
            val listType = object : TypeToken<List<T>>() {
            }.type
            dataList = Gson().fromJson(dataString, listType)
            return dataList
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return dataList
    }
}