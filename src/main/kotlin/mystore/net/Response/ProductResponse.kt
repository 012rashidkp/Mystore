package mystore.net.Response

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
sealed class ProductResponse<T>(
    @JsonIgnore open val statuscode: String,
    @JsonIgnore open val status_description:String,
    @JsonIgnore open val current_time: String?
){
    @JsonSerialize
    data class SuccessResponse<T>(
        val error:Boolean=false,
        val message:String?,

        override val statuscode: String="${HttpStatusCode.OK.value}",
        override val status_description: String= HttpStatusCode.OK.description,
        override val current_time: String?= LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")),
        val products: T?=null
  ):ProductResponse<T>(statuscode,status_description,current_time)
    @JsonSerialize
    data class ErrorResponse<T>(
        val error: Boolean=true,
        val message: String?=null,
        override val statuscode: String="${HttpStatusCode.OK.value}",
        override val status_description: String=HttpStatusCode.OK.description,
        override val current_time: String?= LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"))

    ):ProductResponse<T>(statuscode,status_description,current_time)



}
