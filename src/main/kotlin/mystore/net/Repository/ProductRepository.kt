package mystore.net.Repository

import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.CreateProductParams
import mystore.net.Response.CategoryResponse
import mystore.net.Response.ProductResponse

interface ProductRepository {

    suspend fun Createproduct(params: CreateProductParams): ProductResponse<Any>
}