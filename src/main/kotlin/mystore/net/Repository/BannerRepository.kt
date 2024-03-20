package mystore.net.Repository

import mystore.net.Requests.CreateBannerParams
import mystore.net.Requests.UpdateBannerParams
import mystore.net.Response.BannerResponse

interface BannerRepository {
    suspend fun createbanner(params: CreateBannerParams):BannerResponse<Any>

    suspend fun getAllbanners():BannerResponse<Any>

    suspend fun deletebanner(banner_id:Int):BannerResponse<Any>

    suspend fun updatebanner(params: UpdateBannerParams):BannerResponse<Any>
}