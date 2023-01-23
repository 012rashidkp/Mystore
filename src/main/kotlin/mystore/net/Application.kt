package mystore.net


import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mystore.net.Configuration.configureContentNegotiation
import mystore.net.Configuration.configureDatabase
import mystore.net.Configuration.configureRouting
import mystore.net.Configuration.configureStatusPages
import mystore.net.Security.configureSecurity


fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.36.131"){
     configureDatabase()
     configureContentNegotiation()
     configureStatusPages()
     configureSecurity()
     configureRouting()




    }.start(wait = true)




}


