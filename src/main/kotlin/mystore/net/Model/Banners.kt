package mystore.net.Model

import org.jetbrains.exposed.sql.statements.api.ExposedBlob

data class Banners(
    val banner_id:Int,
    val title:String,
    val description:String,
    val banner_image:String,
    val created_at:String
)
