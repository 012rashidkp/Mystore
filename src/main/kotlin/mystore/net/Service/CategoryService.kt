package mystore.net.Service

import mystore.net.Model.Categories

import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.UpdateCategoryParams


interface CategoryService {
    suspend fun Createcategory(params:CreateCategoryparams):Categories?
    suspend fun findcategoryByName(catName:String):Categories?

    suspend fun getAllcategories():List<Categories?>?

    suspend fun findCategoryId(cat_id:Int):Categories?

    suspend fun deletecategory(category_id:Int):Boolean?

    suspend fun UpdateCategory(params: UpdateCategoryParams):Boolean?
}