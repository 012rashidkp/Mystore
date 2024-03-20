package mystore.net.Service

import mystore.net.Model.Banners
import mystore.net.Requests.CreateBannerParams
import mystore.net.Requests.UpdateBannerParams

interface BannerService {
    suspend fun CreateBanners(params: CreateBannerParams):Banners?

    suspend fun FindBannerbyTitle(title:String):Banners?

    suspend fun getAllBanners():List<Banners?>?

    suspend fun findBannerById(benner_id:Int):Banners?

    suspend fun deleteBanner(banner_id:Int):Boolean

    suspend fun updateBanner(params: UpdateBannerParams):Boolean
}