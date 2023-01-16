package mystore.net.Routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.UserRepository
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams

fun Application.authroutes(repository: UserRepository){
    routing {
        route("/auth"){
            post("/registeruser") {
                val params=call.receive<CreateuserParams>()
                val result=repository.Registeruser(params)
                call.respond(result)
            }
            post("/login"){
                val params = call.receive<UserLoginParams>()
                val result = repository.Loginuser(params)
                call.respond(result)
            }
        }
    }

}