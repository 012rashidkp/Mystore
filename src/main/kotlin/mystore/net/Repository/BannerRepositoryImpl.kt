package mystore.net.Repository

import mystore.net.Constants.PATHURLCONFIG
import mystore.net.Model.Banners
import mystore.net.Model.Categories
import mystore.net.Requests.CreateBannerParams
import mystore.net.Requests.UpdateBannerParams
import mystore.net.Response.BannerResponse
import mystore.net.Response.CategoryResponse
import mystore.net.Service.BannerService

class BannerRepositoryImpl(private val service: BannerService ) : BannerRepository {
    override suspend fun createbanner(params: CreateBannerParams): BannerResponse<Any> {
        return if (isbannerExist(params.title?:"")){
            BannerResponse.ErrorResponse(error = true, message = "${params.title} banner Already Exist")
        }
        else{
            val banner=service.CreateBanners(params)
            if (banner !=null){

                BannerResponse.SuccessResponse(error = false, message = "${params.title} banner created success")

            }
            else{
                BannerResponse.ErrorResponse(error = true, message = "something went wrong")
            }

        }


    }

    override suspend fun getAllbanners(): BannerResponse<Any> {
        val banneritems= mutableListOf<Banners>()
        banneritems.clear()
        val bannerList = service.getAllBanners()
        return if (bannerList!!.isEmpty()){

            BannerResponse.ErrorResponse(error = true, message = "No categories found")
        }
        else{
            for (i in 0 until bannerList.size){
                banneritems.add(Banners(bannerList.get(i)?.banner_id?:0,bannerList.get(i)?.title?:"", bannerList.get(i)?.description?:"","$PATHURLCONFIG${bannerList.get(i)?.banner_image}",bannerList.get(i)?.created_at?:""))
            }


            BannerResponse.SuccessResponse(error = false, message = "categories fetched", banners = banneritems)


        }
    }

    override suspend fun deletebanner(banner_id: Int): BannerResponse<Any> {
        return if (!isbanneridExist(banner_id)){
            BannerResponse.ErrorResponse(error = true, message = "bannerid ${banner_id} doesnot exist")

        }
        else{
            val banner_id=service.deleteBanner(banner_id)
            if (banner_id!=null){
                BannerResponse.SuccessResponse(error = false, message = "Banner deleted success")

            }
            else{
                BannerResponse.ErrorResponse(error = true, message = "seomething went wrong")

            }
        }







    }

    override suspend fun updatebanner(params: UpdateBannerParams): BannerResponse<Any> {
        return if (!isbanneridExist(params.banner_id)){
            BannerResponse.ErrorResponse(error = true, message = "bannerid ${params.banner_id} doesnot exist")

        }
        else{
            val banner=service.updateBanner(params)
            if (banner !=null){

                BannerResponse.SuccessResponse(error = false, message = "Banner updated success")

            }
            else{
                BannerResponse.ErrorResponse(error = true, message = "something went wrong")
            }
        }


    }


    private suspend fun isbannerExist(title: String):Boolean{
        return service.FindBannerbyTitle(title)!=null
    }
    private suspend fun isbanneridExist(banner_id: Int):Boolean{
        return service.findBannerById(banner_id)!=null
    }
}