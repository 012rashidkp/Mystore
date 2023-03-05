package mystore.net.di

import mystore.net.Repository.AuthRepository
import mystore.net.Repository.AuthRepositoryImpl
import mystore.net.Service.AuthServiceImpl

object AuthRepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
}