package mystore.net.Routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.AuthRepository
import mystore.net.Requests.CreateSuperUserParams
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams


fun Application.authroutes(repository: AuthRepository){
    routing {
        route("/auth"){
            post("/registeruser") {
              val parameters=call.receiveParameters()
                val userparams=CreateuserParams(
                    parameters["username"]!!,
                    parameters["email"]!!,
                    parameters["phone"]!!,
                    parameters["city"]!!,
                    parameters["password"]!!,


                )
           val registerresult=repository.Registeruser(userparams)
            call.respond(registerresult)



            }
            post("/createsuperuser"){
                val superparams=call.receiveParameters()
                val superuserparams=CreateSuperUserParams(
                    superparams["email"]!!,
                    superparams["password"]!!,
                    )
                val createresult=repository.Createsuperuser(superuserparams)
                call.respond(createresult)

            }



            post("/login"){
val userparams=call.receiveParameters()
   val loginparams=UserLoginParams(
       userparams["email"]!!,
       userparams["password"]!!
   )
  val loginresult=repository.Loginuser(loginparams)
  call.respond(loginresult)


            }
            post("/loginsuperuser"){
                val userparams=call.receiveParameters()
                val loginparams=UserLoginParams(
                    userparams["email"]!!,
                    userparams["password"]!!
                )
                val loginresult=repository.Loginsuperuser(loginparams)
                call.respond(loginresult)
            }

        }
    }

}









