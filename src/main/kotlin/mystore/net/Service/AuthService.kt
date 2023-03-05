package mystore.net.Service

import mystore.net.Model.Users
import mystore.net.Requests.CreateSuperUserParams
import mystore.net.Requests.CreateuserParams

interface AuthService {
    suspend fun Createuser(params: CreateuserParams):Users?

    suspend fun Loginuser(email: String,password:String):Users?

    suspend fun Loginsuperuser(email: String,password:String):Users?
    suspend fun Createsuperusr(parameters: CreateSuperUserParams):Users?

    suspend fun findUserByEmail(email:String):Users?

    suspend fun findUserphone(phone:String):Users?

}