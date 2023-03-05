package mystore.net.di

import mystore.net.Repository.AuthRepository
import mystore.net.Repository.AuthRepositoryImpl
import mystore.net.Repository.CategoryRepository
import mystore.net.Repository.CategoryRepositoryImpl
import mystore.net.Service.AuthServiceImpl
import mystore.net.Service.CategoryServiceImpl

object CategoryRepositoryProvider {
    fun providecategoryRepository(): CategoryRepository = CategoryRepositoryImpl(CategoryServiceImpl())
}