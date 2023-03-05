package mystore.net.Repository

import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.CreateuserParams
import mystore.net.Response.AuthResponse
import mystore.net.Response.CategoryResponse

interface CategoryRepository {
    suspend fun Createcategory(params:CreateCategoryparams): CategoryResponse<Any>
}