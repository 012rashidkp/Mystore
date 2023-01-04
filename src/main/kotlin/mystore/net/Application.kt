package mystore.net


import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mystore.net.Database.DatabaseFactory
import io.ktor.server.plugins.contentnegotiation.*
import mystore.net.Constants.Server_ipAddress
import mystore.net.Constants.Server_port
import mystore.net.Repository.UserRepository
import mystore.net.Repository.UserRepositoryImpl
import mystore.net.Routes.authroutes
import mystore.net.Security.configureSecurity
import mystore.net.Service.UserService
import mystore.net.Service.UserServiceImpl



fun main() {
    embeddedServer(Netty, port = Server_port, host = "$Server_ipAddress"){
        DatabaseFactory.init()

    install(ContentNegotiation) {
       jackson()

    }
    configureSecurity()
    val service:UserService=UserServiceImpl()
    val repository:UserRepository=UserRepositoryImpl(service)
    authroutes(repository)




    }.start(wait = true)




}


