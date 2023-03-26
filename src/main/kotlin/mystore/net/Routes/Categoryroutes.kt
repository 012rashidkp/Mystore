package mystore.net.Routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.CategoryRepository
import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.UpdateCategoryParams

import java.io.File


fun Application.categoryroute(repository: CategoryRepository) {
    val uploadDir = File("uploads") // specify the directory where uploaded files will be saved
    uploadDir.mkdirs()
    routing {
        route("/category") {

            post("/createcategory") {
                val multipart = call.receiveMultipart()

                var categoryName = ""
                var categoryImage: String? = null

                multipart.forEachPart { part ->
                    // if part is a file (could be form item)
                    if (part is PartData.FileItem && part.name == "categoryImage") {
                        // get the original filename and extension
                        val name = part.originalFileName!!

                        val file = File(uploadDir, name)

                        // check if the file already exists
                        if (file.exists()) {
                            file.delete() // delete the existing file
                        }

                        // use InputStream from part to save file
                        part.streamProvider().use { its ->
                            // copy the stream to the file with buffering
                            file.outputStream().buffered().use {
                                // note that this is blocking
                                its.copyTo(it)
                            }
                        }

                        // set the category image file path
                        categoryImage = "/uploads/" + file.name
                    } else if (part is PartData.FormItem && part.name == "categoryName") {
                        categoryName = part.value
                    }

                    // make sure to dispose of the part after use to prevent leaks
                    part.dispose()
                }

                // create the category entity
                val category = CreateCategoryparams(categoryName, categoryImage!!)
                val createResult = repository.Createcategory(category)

                // return a response with just the image filename
                call.respond(createResult)
            }

            get("/getcategories"){
                val getAllcategories=repository.getAllcategories()
                val baseUrl = "${call.request.origin.scheme}://${call.request.origin.host}:${call.request.origin.port}"
                 System.out.println("mybaseurl.. $baseUrl")


                call.respond(getAllcategories)
            }
            post("/deletecategory"){
                val category_id=call.parameters["category_id"]?.toInt()
                val deletecategory=repository.deletecategory(category_id!!)
                call.respond(deletecategory)

            }
            post("/updatecategory") {
                val multipart = call.receiveMultipart()

                var category_id = 0
                var categoryName = ""
                var categoryImage: String? = null

                multipart.forEachPart { part ->
                    // if part is a file (could be form item)
                    if (part is PartData.FileItem && part.name == "categoryImage") {
                        // get the original filename and extension
                        val name = part.originalFileName!!
                    
                        val file = File(uploadDir, name)

                        // check if the file already exists
                        if (file.exists()) {
                            file.delete() // delete the existing file
                        }

                        // use InputStream from part to save file
                        part.streamProvider().use { its ->
                            // copy the stream to the file with buffering
                            file.outputStream().buffered().use {
                                // note that this is blocking
                                its.copyTo(it)
                            }
                        }

                        // set the category image file path
                        categoryImage = "/uploads/" + file.name
                    } else if (part is PartData.FormItem && part.name == "categoryName") {
                        categoryName = part.value
                    } else if (part is PartData.FormItem && part.name == "category_id") {
                        category_id = part.value.toInt()
                    }

                    // make sure to dispose of the part after use to prevent leaks
                    part.dispose()
                }

                // update the category entity
                val category = UpdateCategoryParams(category_id, categoryName, categoryImage!!)
                val updateResult = repository.updatecategory(category)

                // return a response with just the image filename
                call.respond(updateResult)
            }








        }
    }
}
