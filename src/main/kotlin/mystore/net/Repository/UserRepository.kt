package mystore.net.Repository

import mystore.net.Model.BaseResponse
import mystore.net.Service.CreateuserParams

interface UserRepository {
    suspend fun Registeruser(params: CreateuserParams):BaseResponse<Any>
    suspend fun Loginuser(email:String,password:String):BaseResponse<Any>
}