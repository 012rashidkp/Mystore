package mystore.net.di

import mystore.net.Repository.ProductRepository
import mystore.net.Repository.ProductRepositoryImpl
import mystore.net.Service.ProductServiceImpl

object ProductRepositoryProvider {
    fun provideproductRepository():ProductRepository=ProductRepositoryImpl(ProductServiceImpl())
}