package mystore.net.Routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Repository.CategoryRepository
import mystore.net.Requests.CreateCategoryparams
import org.jetbrains.exposed.sql.statements.api.ExposedBlob

fun Application.categoryroute(repository: CategoryRepository){
    routing {
        route("/category"){

                    post("/createcategory") {

                        val multipart = call.receiveMultipart()
                        var categoryName = ""
                        var categoryImage: PartData.FileItem? = null
                        multipart.forEachPart { part ->
                            when (part) {
                                is PartData.FormItem -> {
                                    when (part.name) {
                                        "categoryName" -> categoryName = part.value
                                    }
                                }
                                is PartData.FileItem -> {
                                    if (part.name == "categoryimage") {
                                        categoryImage = part
                                    }
                                }
                                else -> {}
                            }
                            part.dispose()
                        }

                        val params = CreateCategoryparams(
                            categoryName,
                            categoryImage?.let { ExposedBlob(it.streamProvider().readBytes()) } ?: ExposedBlob(byteArrayOf())


                        )

                        val result = repository.Createcategory(params)
                        call.respond(result)
                    }



        }
    }
}