package mystore.net.Requests

data class UpdateCategoryParams(
    val category_id:Int,
    val categoryName:String,
    val categoryImage:String
)
