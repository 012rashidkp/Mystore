package mystore.net.di

import mystore.net.Repository.UserRepository
import mystore.net.Repository.UserRepositoryImpl
import mystore.net.Service.UserServiceImpl

object RepositoryProvider {
    fun provideAuthRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
}