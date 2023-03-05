package mystore.net


import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mystore.net.Configuration.*

import mystore.net.Security.configureSecurity


fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.166.131"){
     configureDatabase()
     configureContentNegotiation()
     configureStatusPages()
     configureSecurity()
     configureAuthRouting()
     configureCategoryRouting()

    }.start(wait = true)




}


