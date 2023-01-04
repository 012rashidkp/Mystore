package mystore.net.Service

import mystore.net.Model.Users

interface UserService {
    suspend fun Createuser(params: CreateuserParams):Users?

    suspend fun findUserByEmail(email:String):Users?

}