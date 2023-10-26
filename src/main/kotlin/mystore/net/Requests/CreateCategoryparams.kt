package mystore.net.Requests

import io.ktor.http.content.*
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.postgresql.largeobject.BlobInputStream
import java.io.File
import java.sql.Blob


data class CreateCategoryparams(
val categoryName:String,
val categoryImage:String?

)
