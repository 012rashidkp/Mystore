package mystore.net.Routes

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Constants.*
import mystore.net.Repository.CategoryRepository
import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.UpdateCategoryParams
import org.jetbrains.exposed.sql.statements.api.ExposedBlob

import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import kotlin.reflect.jvm.reflect


fun Application.categoryroute(repository: CategoryRepository) {


    routing {
authenticate {
    route("/category") {

        post("/createcategory"){
            val multipart = call.receiveMultipart()
            var categoryName: String? = null
            var imageUrl: String? = null
            var fileName: String? = null
            multipart.forEachPart {partData ->
                when(partData){

                    is PartData.FormItem ->{
                        if (partData.name== CategoryName){
                            categoryName=partData.value
                        }
                    }
                    is PartData.FileItem -> {
                        if (partData.name== CategoryImage){
                            fileName = partData.save(CATEGORY_IMAGE_PATH)
                            imageUrl = "${EXTERNAL_CATEGORY_IMAGE_PATH}/$fileName"


                        }
                    }
                    is PartData.BinaryChannelItem ->{}
                    is PartData.BinaryItem ->{}
                }
                partData.dispose()



            }
            val category = CreateCategoryparams(categoryName?:"", imageUrl)
            val createResult = repository.Createcategory(category)
            call.respond(createResult)





        }












        get("/getcategories"){

            val getAllcategories=repository.getAllcategories()

            call.respond(getAllcategories)
        }
        post("/deletecategory"){
            val category_id=call.parameters["category_id"]?.toInt()
            val deletecategory=repository.deletecategory(category_id?:0)
            call.respond(deletecategory)

        }


        post("/updatecategory") {
            val multipart = call.receiveMultipart()

            var category_id = 0
            var categoryName = ""
            lateinit var imageFile: File

            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FileItem -> {
                        val name = part.originalFileName ?: "unknown"
                        imageFile = File("upload/image/$name") // Save to upload/image directory
                        if (imageFile.exists()) {
                            imageFile.delete()
                        }
                        part.streamProvider().use { input ->
                            imageFile.outputStream().buffered().use { output ->
                                input.copyTo(output)
                            }
                        }
                    }
                    is PartData.FormItem -> {
                        if (part.name == "categoryName") {
                            categoryName = part.value
                        }
                        else if (part.name=="category_id"){
                            category_id=part.value.toInt()

                        }
                    }

                    is PartData.BinaryChannelItem -> {
                        // Handle binary channel items if needed
                    }

                    is PartData.BinaryItem -> {
                        // Handle binary items if needed
                    }
                }

                part.dispose()
            }



            val imageFilePath = "upload/categories/${imageFile.name}" // Store the image path

            // update the category entity
            val category = UpdateCategoryParams(category_id, categoryName, imageFilePath)
            val updateResult = repository.updatecategory(category)

            // return a response with just the image filename
            call.respond(updateResult)
        }








    }
}




        static("/"){
        staticRootFolder=File(STATIC_ROOT)
        static(EXTERNAL_CATEGORY_IMAGE_PATH){

            files(CATEGORY_IMAGE_DIRECTORY)


        }


        }



    }
}

