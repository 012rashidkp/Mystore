package mystore.net.Requests

data class CreateProductParams(
    val product_Name:String,
    val product_desc:String,
    val category_id:Int,
    val marked_price:Double,
    val selling_price:Double,
    val product_image:String,


)
