package mystore.net.Repository

import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.UpdateCategoryParams

import mystore.net.Response.CategoryResponse

interface CategoryRepository {
    suspend fun Createcategory(params:CreateCategoryparams): CategoryResponse<Any>

    suspend fun getAllcategories():CategoryResponse<Any>

    suspend fun deletecategory(category_id:Int):CategoryResponse<Any>

    suspend fun updatecategory(params: UpdateCategoryParams):CategoryResponse<Any>

}