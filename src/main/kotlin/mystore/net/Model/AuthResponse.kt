package mystore.net.Model

import io.ktor.http.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

sealed class AuthResponse<T>(
    val statuscode: Int =HttpStatusCode.OK.value,
    val status_description:String=HttpStatusCode.OK.description,
    val current_time: String? ="${LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))}"
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
        var authtoken:String?=null,



            ):AuthResponse<T>()
    data class ErrorResponse<T>(
        val error: Boolean=true,
        val message: String?=null,
        val exception:T?=null

    ):AuthResponse<T>()
}
