package mystore.net.Requests

import org.jetbrains.exposed.sql.statements.api.ExposedBlob

data class UpdateCategoryParams(
    val category_id:Int,
    val categoryName:String,
    val categoryImage:String?
)
