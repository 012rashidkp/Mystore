package mystore.net.Routes

import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mystore.net.Constants.*
import mystore.net.Repository.ProductRepository
import mystore.net.Requests.CreateProductParams
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import java.io.File

fun Application.productroute(repository: ProductRepository){

    routing {
       authenticate {
           route("/product"){
               post("/createproduct"){
                   val multipart = call.receiveMultipart()
                   var productname:String?=null
                   var product_desc:String?=null
                   var category_id:Int?=null
                   var marked_price:Double?=null
                   var selling_price:Double?=null
                   var imageurl:String?=null
                   var fileName: String? = null
                   multipart.forEachPart {partData ->
                       when(partData){
                           is PartData.FormItem -> {
                               if (partData.name == productName_params){
                                   productname=partData.value
                               }
                               if (partData.name == productdesc_params){
                                   product_desc=partData.value
                               }
                               if (partData.name == categoryid_params){
                                   category_id=partData.value.toInt()
                               }
                               if (partData.name == markedprice_params){
                                   marked_price=partData.value.toDouble()
                               }
                               if (partData.name == sellingprice_params){
                                   selling_price=partData.value.toDouble()
                               }
                           }
                           is PartData.FileItem ->{
                             if (partData.name == productimage_params){
                                 fileName = partData.save(PRODUCT_IMAGE_PATH)

                                 imageurl="$EXTERNAL_PRODUCT_IMAGE_PATH/$fileName"
                             }




                           }
                           is PartData.BinaryChannelItem -> {}
                           is PartData.BinaryItem -> {}
                       }
                       partData.dispose()


                   }
                   val productinput=CreateProductParams(
                       product_Name = productname?:"",
                       product_desc = product_desc?:"",
                       category_id = category_id?:0,
                       marked_price = marked_price?:0.0,
                       selling_price = selling_price?:0.0,
                       product_image = imageurl?:""
                   )
                   val createresult=repository.Createproduct(productinput)
                   call.respond(createresult)








               }

               get("/getproducts"){
                   val getAllproducts=repository.getAllproducts()
                   call.respond(getAllproducts)
               }


           }

       }




        static("/"){
            staticRootFolder= File(STATIC_ROOT)
            static(EXTERNAL_PRODUCT_IMAGE_PATH){
                files(PRODUCT_IMAGE_DIRECTORY)

            }
        }


    }
}