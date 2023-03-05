package mystore.net.Response

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
sealed class AuthResponse<T>(
    @JsonIgnore open val statuscode: String,
    @JsonIgnore open val status_description:String,
    @JsonIgnore open val current_time: String?
    ){
    @JsonSerialize
    data class SuccessResponse<T>(
        val error:Boolean=false,
        val message:String?,
        val userid:String?,
        val username:String,
        val email:String,
        val phone:String,
        val city:String,
        val createdAt:String,
        val is_superuser:Boolean,
        var authtoken:String?,

        @JsonIgnore
        override val statuscode: String="${HttpStatusCode.OK.value}",
        @JsonIgnore
        override val status_description: String=HttpStatusCode.OK.description,
        @JsonIgnore
        override val current_time: String?="${LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))}"


            ): AuthResponse<T>(statuscode,status_description,current_time)
    data class ErrorResponse<T>(
        val error: Boolean=true,
        val message: String?=null,
        val exception:T?=null,
        @JsonIgnore
        override val statuscode: String="${HttpStatusCode.OK.value}",
        @JsonIgnore
        override val status_description: String=HttpStatusCode.OK.description,
        @JsonIgnore
        override val current_time: String?="${LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))}"

    ): AuthResponse<T>(statuscode,status_description,current_time)
}
