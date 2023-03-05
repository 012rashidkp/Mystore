package mystore.net.Service

import mystore.net.Model.Categories
import mystore.net.Model.Users
import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.CreateuserParams

interface CategoryService {
    suspend fun Createcategory(params:CreateCategoryparams):Categories?

    suspend fun findcategoryByName(catName:String):Categories?
}