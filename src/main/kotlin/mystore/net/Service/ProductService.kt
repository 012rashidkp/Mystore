package mystore.net.Service

import mystore.net.Model.Categories
import mystore.net.Model.Products
import mystore.net.Requests.CreateProductParams

interface ProductService {

    suspend fun createproduct(params:CreateProductParams):Products?

    suspend fun getproductName(productName:String):Products?
    suspend fun findCategorybyId(cat_id:Int): Categories?

}