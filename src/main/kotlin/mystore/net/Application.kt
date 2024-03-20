package mystore.net



import io.ktor.server.engine.*
import io.ktor.server.netty.*
import mystore.net.Configuration.*
import mystore.net.Constants.BASE_URL
import mystore.net.Constants.PORT
import mystore.net.Security.configureSecurity



fun main() {
    embeddedServer(Netty, port = PORT, host = BASE_URL?:""){
     configureDatabase()
     configureContentNegotiation()
     configureStatusPages()
     configureSecurity()
     configureAuthRouting()
     configureBannerRouting()
     configureCategoryRouting()
     configureProductRouting()


    }.start(wait = true)




}


