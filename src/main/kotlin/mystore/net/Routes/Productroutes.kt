package mystore.net.Routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.ProductRepository
import mystore.net.Requests.CreateProductParams
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import java.io.File

fun Application.productroute(repository: ProductRepository){

    routing {
        route("/product"){
            post("/createproduct"){
                val multipart = call.receiveMultipart()

                var product_Name = ""
                var product_desc=""
                var category_id=0
                var marked_price=0.0
                var selling_price=0.0
                var imageFileName = ""
                multipart.forEachPart { part ->
                    when(part){
                        is PartData.FileItem ->{
                            if (part.name == "product_image"){
                                val extension = File(part.originalFileName).extension
                                imageFileName = "product_${System.currentTimeMillis()}.$extension"
                                val uploadDir = File("/upload/product")
                                uploadDir.mkdirs()

                                val file = File(uploadDir, imageFileName)
//                            if (file.exists()) {
//                                file.delete() // delete the existing file
//                            }

                                part.streamProvider().use { input ->
                                    file.outputStream().buffered().use { output ->
                                        input.copyTo(output)
                                    }
                                }

                            }

                        }
                        is PartData.FormItem -> {
                            if (part.name == "product_Name"){
                                product_Name=part.value
                            }
                            else if (part.name == "product_desc"){
                                product_desc=part.value
                            }
                            else if (part.name == "category_id"){
                                category_id=part.value.toInt()
                            }
                            else if (part.name == "marked_price"){
                                marked_price=part.value.toDouble()
                            }
                            else if (part.name == "selling_price"){
                                selling_price=part.value.toDouble()
                            }

                        }
                        is PartData.BinaryChannelItem -> {

                        }
                        is PartData.BinaryItem -> {

                        }
                    }
                    part.dispose()

                }






                val imagePath = "/upload/product/$imageFileName"

                val product=CreateProductParams(product_Name,product_desc,category_id,marked_price,selling_price,imagePath)
                val productcreateresult=repository.Createproduct(product)
                call.respond(productcreateresult)
            }
        }
    }
}