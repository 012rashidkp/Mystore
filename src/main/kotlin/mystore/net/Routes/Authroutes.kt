package mystore.net.Routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.UserRepository
import mystore.net.Service.CreateuserParams

fun Application.authroutes(repository: UserRepository){
    routing {
        route("/auth"){
            post("/registeruser") {
                val params=call.receive<CreateuserParams>()
                val result=repository.Registeruser(params)
                call.respond(result)
            }
        }
    }

}