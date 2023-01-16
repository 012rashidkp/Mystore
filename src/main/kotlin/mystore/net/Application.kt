package mystore.net


import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mystore.net.Database.DatabaseFactory
import io.ktor.server.plugins.contentnegotiation.*
import mystore.net.Configuration.configureContentNegotiation
import mystore.net.Configuration.configureDatabase
import mystore.net.Configuration.configureRouting
import mystore.net.Configuration.configureStatusPages
import mystore.net.Constants.Server_ipAddress
import mystore.net.Constants.Server_port
import mystore.net.Repository.UserRepository
import mystore.net.Repository.UserRepositoryImpl
import mystore.net.Routes.authroutes
import mystore.net.Security.configureSecurity
import mystore.net.Service.UserService
import mystore.net.Service.UserServiceImpl



fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.152.131"){
     configureDatabase()
     configureContentNegotiation()
     configureStatusPages()
     configureSecurity()
     configureRouting()




    }.start(wait = true)




}


