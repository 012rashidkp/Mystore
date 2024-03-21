package mystore.net.Repository

import mystore.net.Constants.PATHURLCONFIG
import mystore.net.Model.Banners
import mystore.net.Model.Products
import mystore.net.Requests.CreateProductParams
import mystore.net.Response.CategoryResponse
import mystore.net.Response.ProductResponse
import mystore.net.Service.ProductService

class ProductRepositoryImpl(private val productService: ProductService) : ProductRepository {
    override suspend fun Createproduct(params: CreateProductParams): ProductResponse<Any> {
          return if (params.product_Name.isBlank()){
              ProductResponse.ErrorResponse(
                  error = true,
                  message =
                  "product name cannot be blank"
              )

          }
       else if (params.product_desc.isBlank()){
              ProductResponse.ErrorResponse(error = true, message = "product description cannot be blank")

          }
          else if (params.category_id == 0) {
              ProductResponse.ErrorResponse(error = true, message = "category_id cannot be blank or Zero")
          }


       else if (!iscategoryExist(params.category_id)){
              ProductResponse.ErrorResponse(error = true, message = "categoryid ${params.category_id} doesnot exist")

          }
         else if (params.marked_price == 0.0){
              ProductResponse.ErrorResponse(error = true, message = "marked price cannot be blank or less than 1")

          }
          else if (params.selling_price == 0.0){
              ProductResponse.ErrorResponse(error = true, message = "selling price cannot be blank or less than 1")

          }
          else if (params.product_image.isEmpty()){
              ProductResponse.ErrorResponse(error = true, message = "product image cannot be blank")

          }




        else if (isproductNameExist(params.product_Name)){
            System.out.println("my_params... ${params}")
              ProductResponse.ErrorResponse(error = true, message = "product ${params.product_Name} Already exist")
        }
        else{
            val products=productService.createproduct(params)
              if (products !=null){
                  ProductResponse.SuccessResponse(error = false, message = "product created success")

              }
              else{
                  ProductResponse.ErrorResponse(error = true, message = "something went wrong")
              }
          }
    }

    override suspend fun getAllproducts(): ProductResponse<Any> {
        val productitems= mutableListOf<Products>()
        productitems.clear()
        val productlist=productService.getAllproducts()
        return  if (productlist!!.isEmpty()){
            ProductResponse.ErrorResponse(
                error = true,
                message = "No Products Found"
            )
        }
        else{
            for ( i in 0 until productlist.size){
                productitems.add(Products(
                 product_id = productlist.get(i)?.product_id?:0,
                 product_Name = productlist.get(i)?.product_Name?:"",
                 product_desc = productlist.get(i)?.product_desc?:"",
                 category_id = productlist.get(i)?.category_id?:0,
                 categoryName = productlist.get(i)?.categoryName,
                 marked_price = productlist.get(i)?.marked_price?:0.0,
                 selling_price = productlist.get(i)?.selling_price?:0.0,
                 product_image = "$PATHURLCONFIG${productlist.get(i)?.product_image}",
                 created_at = productlist.get(i)?.created_at?:""

                ))
            }
            ProductResponse.SuccessResponse(error = false, message = "products fetched", products = productitems)
        }




    }

    private suspend fun isproductNameExist(productName:String):Boolean{
        return productService.getproductName(productName)!=null
    }
    private suspend fun iscategoryExist(cat_id:Int):Boolean{

        return productService.findCategorybyId(cat_id)!=null
    }
}