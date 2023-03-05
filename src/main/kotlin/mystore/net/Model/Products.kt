package mystore.net.Model

import org.jetbrains.exposed.sql.statements.api.ExposedBlob

data class Products(
    val product_id:Int,
    val product_Name:String,
    val product_desc:String,
    val category_id:Int,
    val categories: Categories?=null,
    val marked_price:Double,
    val selling_price:Double,
    val product_image:ExposedBlob,
    val created_at:String
)
