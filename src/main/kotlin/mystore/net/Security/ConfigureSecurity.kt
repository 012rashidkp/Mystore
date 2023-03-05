package mystore.net.Security


import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import mystore.net.Response.AuthResponse

fun Application.configureSecurity(){
    JwtConfig.initialize("mystore")
    install(Authentication){
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
                if(claim != null){
                    UserIdPrincipalForUser(claim)
                }else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(AuthResponse.ErrorResponse(error = true, message = "invalid token",exception = null))

            }
        }
    }
}


