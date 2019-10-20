package com.dotywar.service

import com.dotywar.vos.FundingResponse
import com.dotywar.vos.NotificationsResponse
import com.dotywar.vos.ReportsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface FundingAPI {

    @GET("v1/fundings")
    @Headers("Accept:application/json")
    fun getFundings(): Observable<FundingResponse>

    @GET("v1/notifications")
    @Headers("Accept:application/json")
    fun getNotifications(): Observable<NotificationsResponse>

    @GET("v1/reports")
    @Headers("Accept:application/json")
    fun getReports(): Observable<ReportsResponse>


}