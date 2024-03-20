package mystore.net.di

import mystore.net.Repository.BannerRepository
import mystore.net.Repository.BannerRepositoryImpl
import mystore.net.Repository.CategoryRepository
import mystore.net.Repository.CategoryRepositoryImpl
import mystore.net.Service.BannerServiceImpl
import mystore.net.Service.CategoryServiceImpl

object BannerRepositoryProvider {
    fun providebannerRepository(): BannerRepository = BannerRepositoryImpl(BannerServiceImpl())
}