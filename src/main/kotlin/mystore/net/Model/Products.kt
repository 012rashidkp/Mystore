package mystore.net.Model


data class Products(
    val product_id:Int,
    val product_Name:String,
    val product_desc:String,
    val category_id:Int,
    val categoryName: String?=null,
    val marked_price:Double,
    val selling_price:Double,
    val product_image:String?,
    val created_at:String
)
