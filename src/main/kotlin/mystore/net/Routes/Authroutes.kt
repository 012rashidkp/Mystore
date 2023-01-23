package mystore.net.Routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.AuthRepository
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams

fun Application.authroutes(repository: AuthRepository){
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


//post("/upload") {
//    val multipart = call.receiveMultipart()
//    multipart.forEachPart { part ->
//        when (part) {
//            is PartData.FileItem -> {
//                val ext = File(part.originalFileName).extension
//                val file = File("$UPLOAD_DIR/${UUID.randomUUID()}.$ext")
//                part.streamProvider().use { its -> file.outputStream().buffered().use { os -> its.copyTo(os) } }
//                // save file path to table
//            }
//            else -> part.dispose()
//        }
//    }
//    call.respondText("File upload successful")
//}
