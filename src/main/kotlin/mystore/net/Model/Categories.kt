package mystore.net.Model

import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import java.sql.Blob


data class Categories(
    val category_id:Int,
    val categoryName:String,
    val category_image:String?,
    val created_at:String
)
