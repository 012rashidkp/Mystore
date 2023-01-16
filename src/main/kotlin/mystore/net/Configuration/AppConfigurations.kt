package mystore.net.Configuration

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import mystore.net.Database.DatabaseFactory
import mystore.net.Routes.authroutes
import mystore.net.di.RepositoryProvider

fun configureDatabase() {
    DatabaseFactory.init()
}
fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}
fun Application.configureRouting(){
    authroutes(RepositoryProvider.provideAuthRepository())

}