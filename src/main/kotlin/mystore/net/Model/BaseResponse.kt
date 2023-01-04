package mystore.net.Model

import io.ktor.http.*
import io.netty.handler.codec.http.HttpResponse

sealed class BaseResponse<T>(
   val statuscode:HttpStatusCode=HttpStatusCode.OK
    ){
    data class SuccessResponse<T>(
        val error:Boolean=false,
        val message:String?=null,
        val userid:Int,
        val username:String,
        val email:String,
        val phone:String,
        val city:String,
        val createdAt:String,
        val is_superuser:Boolean,
        var authtoken:String?=null


            ):BaseResponse<T>()
    data class ErrorResponse<T>(
        val error: Boolean=true,
        val message: String?=null,
        val exception:T?=null

    ):BaseResponse<T>()
}
