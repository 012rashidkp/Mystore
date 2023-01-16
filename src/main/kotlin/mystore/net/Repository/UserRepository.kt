package mystore.net.Repository

import mystore.net.Model.BaseResponse
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams

interface UserRepository {
    suspend fun Registeruser(params: CreateuserParams):BaseResponse<Any>
    suspend fun Loginuser(params: UserLoginParams):BaseResponse<Any>
}