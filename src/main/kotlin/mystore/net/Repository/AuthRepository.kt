package mystore.net.Repository

import mystore.net.Response.AuthResponse
import mystore.net.Requests.CreateSuperUserParams
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams

interface AuthRepository {
    suspend fun Registeruser(params: CreateuserParams): AuthResponse<Any>
    suspend fun Loginuser(params: UserLoginParams): AuthResponse<Any>

    suspend fun Createsuperuser(params: CreateSuperUserParams): AuthResponse<Any>

    suspend fun Loginsuperuser(params: UserLoginParams): AuthResponse<Any>
}