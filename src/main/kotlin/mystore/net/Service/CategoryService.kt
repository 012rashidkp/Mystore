package mystore.net.Service

import mystore.net.Model.Categories

import mystore.net.Requests.CreateCategoryparams


interface CategoryService {
    suspend fun Createcategory(params:CreateCategoryparams):Categories?
    suspend fun findcategoryByName(catName:String):Categories?

    suspend fun getAllcategories():List<Categories?>?
}