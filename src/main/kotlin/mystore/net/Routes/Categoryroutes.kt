package mystore.net.Routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.CategoryRepository
import mystore.net.Requests.CreateCategoryparams

import java.io.File
import java.util.*

fun Application.categoryroute(repository: CategoryRepository) {
    val uploadDir = File("uploads") // specify the directory where uploaded files will be saved
    uploadDir.mkdirs()
    routing {
        route("/category") {
            post("/createcategory") {
                // retrieve all multipart data (suspending)
                val multipart = call.receiveMultipart()

                var categoryName = ""
                var categoryImage: String? = null

                multipart.forEachPart { part ->
                    // if part is a file (could be form item)
                    if (part is PartData.FileItem && part.name == "categoryImage") {
                        // retrieve file name of upload
                        val name = part.originalFileName!!
                        val file = File(uploadDir, UUID.randomUUID().toString() + "." + name.substringAfterLast("."))

                        // use InputStream from part to save file
                        part.streamProvider().use { its ->
                            // copy the stream to the file with buffering
                            file.outputStream().buffered().use {
                                // note that this is blocking
                                its.copyTo(it)
                            }
                        }

                        // set the category image file path
                        categoryImage = file.absolutePath
                    } else if (part is PartData.FormItem && part.name == "categoryName") {
                        categoryName = part.value
                    }

                    // make sure to dispose of the part after use to prevent leaks
                    part.dispose()
                }

                // create the category entity
                val category = CreateCategoryparams(categoryName, categoryImage!!)
                val createResult = repository.Createcategory(category)

                // return a response
                call.respond(createResult)
            }
            get("/getcategories"){
                val getAllcategories=repository.getAllcategories()
                call.respond(getAllcategories)
            }

        }
    }
}
