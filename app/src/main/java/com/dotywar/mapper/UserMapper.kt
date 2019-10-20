package com.dotywar.mapper

import com.daniel.appbase.mappers.UnidirectionalMap
import com.dotywar.vos.LoginResponse
import com.dotywar.vos.UserVO

class UserMapper : UnidirectionalMap<LoginResponse, UserVO> {
    override fun map(item: LoginResponse): UserVO {
        return UserVO(
            userName = item.userName,
            phoneNumber = item.phoneNumber,
            address = item.address,
            profession = item.profession
        )
    }

}