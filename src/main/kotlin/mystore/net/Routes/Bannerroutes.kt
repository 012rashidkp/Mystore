package mystore.net.Routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Constants.*
import mystore.net.Repository.BannerRepository
import mystore.net.Requests.CreateBannerParams
import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.UpdateBannerParams
import java.io.File

fun Application.bannerroute(repository: BannerRepository){
    routing {
        authenticate {
            route("/banner"){
                post("/createbanner") {
                    val multipart = call.receiveMultipart()
                    var title: String? = null
                    var description:String?=null
                    var imageUrl: String? = null
                    var fileName: String? = null
                    multipart.forEachPart {partData ->
                        when(partData){
                            is PartData.FormItem ->{
                                if (partData.name== title_param){
                                   title =partData.value
                                }
                                if (partData.name== description_param){
                                    description=partData.value
                                }
                            }
                            is PartData.FileItem -> {
                                if (partData.name== banner_image_param){
                                    fileName = partData.save(BANNER_IMAGE_PATH)
                                    imageUrl = "$EXTERNAL_BANNER_IMAGE_PATH/$fileName"


                                }
                            }
                            is PartData.BinaryChannelItem -> {}
                            is PartData.BinaryItem -> {}


                        }
                        partData.dispose()




                    }
                    val category = CreateBannerParams(title?:"", description,imageUrl)
                    val createResult = repository.createbanner(category)
                    call.respond(createResult)




                }
                get("/getbanners"){
                    val getAllbanners=repository.getAllbanners()

                    call.respond(getAllbanners)
                }

                //update banner

                post("/updatebanner") {
                    val multipart = call.receiveMultipart()
                    var banner_id:Int?=null
                    var title: String? = null
                    var description:String?=null
                    var imageUrl: String? = null
                    var fileName: String? = null
                    multipart.forEachPart {partData ->
                        when(partData){
                            is PartData.FormItem ->{
                                if (partData.name== banner_id_param){
                                    banner_id=partData.value.toInt()
                                }

                                if (partData.name== title_param){
                                    title =partData.value
                                }
                                if (partData.name== description_param){
                                    description=partData.value
                                }

                            }
                            is PartData.FileItem -> {
                                if (partData.name== banner_image_param){
                                    fileName = partData.save(BANNER_IMAGE_PATH)
                                    imageUrl = "$EXTERNAL_BANNER_IMAGE_PATH/$fileName"


                                }
                            }
                            is PartData.BinaryChannelItem -> {}
                            is PartData.BinaryItem -> {}


                        }
                        partData.dispose()




                    }
                    val banner = UpdateBannerParams(banner_id?:0,title?:"", description,imageUrl)
                    val createResult = repository.updatebanner(banner)
                    call.respond(createResult)




                }
                // delete
                post("/deletebanner"){
                    val banner_id=call.parameters["banner_id"]?.toInt()
                    val deletebanner=repository.deletebanner(banner_id?:0)
                    call.respond(deletebanner)

                }







            }





        }

        static("/"){
            staticRootFolder= File(STATIC_ROOT)
            static(EXTERNAL_BANNER_IMAGE_PATH){
                files(BANNER_IMAGE_DIRECTORY)

            }
        }


    }
}