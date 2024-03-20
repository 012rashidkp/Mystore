package mystore.net.Requests

data class UpdateBannerParams(
    val banner_id:Int,
    val title:String?,
    val description:String?,
    val banner_image:String?
)
