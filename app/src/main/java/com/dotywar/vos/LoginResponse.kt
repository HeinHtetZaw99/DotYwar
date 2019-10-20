package com.dotywar.vos

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("username")
    var userName: String?,
    @SerializedName("phoneNumber")
    var phoneNumber: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("profession")
    var profession: String?,
    @SerializedName("code")
    var code: Int,
    @SerializedName("token")
    var accessToken: String?
)