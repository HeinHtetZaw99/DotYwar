package com.dotywar.service

import com.dotywar.vos.LoginResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LoginAPI {

    @GET("v1/login")
    @Headers("Accept:application/json")
    fun login(@Query("phoneNumber") phone: String): Observable<LoginResponse>

}