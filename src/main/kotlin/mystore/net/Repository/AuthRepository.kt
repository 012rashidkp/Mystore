package mystore.net.Repository

import mystore.net.Model.AuthResponse
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams

interface AuthRepository {
    suspend fun Registeruser(params: CreateuserParams):AuthResponse<Any>
    suspend fun Loginuser(params: UserLoginParams):AuthResponse<Any>
}