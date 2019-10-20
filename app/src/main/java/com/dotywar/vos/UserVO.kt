package com.dotywar.vos

import com.google.gson.annotations.SerializedName

data class UserVO(
    @SerializedName("username")
    var userName: String?,
    @SerializedName("phoneNumber")
    var phoneNumber: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("profession")
    var profession: String?

)