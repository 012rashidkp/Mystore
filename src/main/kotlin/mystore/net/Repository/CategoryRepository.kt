package mystore.net.Repository

import mystore.net.Requests.CreateCategoryparams

import mystore.net.Response.CategoryResponse

interface CategoryRepository {
    suspend fun Createcategory(params:CreateCategoryparams): CategoryResponse<Any>

    suspend fun getAllcategories():CategoryResponse<Any>

}