package mystore.net.Configuration

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import mystore.net.Model.AuthResponse


fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<MismatchedInputException> { call, cause ->
            if(cause is MissingKotlinParameterException) {
                val error = when (cause) {
                    is MissingKotlinParameterException -> AuthResponse.ErrorResponse(error = true, message = "Missing attribute `${cause.parameter.name}`",exception = null)
                    else -> AuthResponse.ErrorResponse(error = true,message = cause.message)
                }
                call.respond(error)

            }
            else {
                call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
            }

        }
    }




}


