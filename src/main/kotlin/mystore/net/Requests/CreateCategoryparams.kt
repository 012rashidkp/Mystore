package mystore.net.Requests

import org.jetbrains.exposed.sql.statements.api.ExposedBlob

data class CreateCategoryparams(
val categoryName:String,
val categoryImage:ExposedBlob

)
