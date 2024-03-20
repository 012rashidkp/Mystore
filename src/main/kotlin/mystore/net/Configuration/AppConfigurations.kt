package mystore.net.Configuration

import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import mystore.net.Database.DatabaseFactory
import mystore.net.Routes.authroutes
import mystore.net.Routes.bannerroute
import mystore.net.Routes.categoryroute
import mystore.net.Routes.productroute
import mystore.net.di.AuthRepositoryProvider
import mystore.net.di.BannerRepositoryProvider
import mystore.net.di.CategoryRepositoryProvider
import mystore.net.di.ProductRepositoryProvider

fun configureDatabase() {
    DatabaseFactory.init()
}
fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}
fun Application.configureAuthRouting(){
    authroutes(AuthRepositoryProvider.provideAuthRepository())

}
fun Application.configureCategoryRouting(){
    categoryroute(CategoryRepositoryProvider.providecategoryRepository())

}

fun Application.configureBannerRouting(){
  bannerroute(BannerRepositoryProvider.providebannerRepository())
}


fun Application.configureProductRouting(){
    productroute(ProductRepositoryProvider.provideproductRepository())
}