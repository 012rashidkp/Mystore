package mystore.net.Repository

import mystore.net.Requests.CreateCategoryparams

import mystore.net.Response.CategoryResponse

import mystore.net.Service.CategoryService

class CategoryRepositoryImpl(private val categoryService: CategoryService) : CategoryRepository {
    override suspend fun Createcategory(params: CreateCategoryparams): CategoryResponse<Any> {
        return if (iscategoryExist(params.categoryName)){
            CategoryResponse.ErrorResponse(error = true, message = "${params.categoryName} category Already Exist")
        }
        else{
            val category=categoryService.Createcategory(params)
            if (category !=null){

                CategoryResponse.SuccessResponse(error = false, message = "Category created succes")
            }
            else{
                CategoryResponse.ErrorResponse(error = true, message = "something went wrong")
            }

        }

    }

    override suspend fun getAllcategories(): CategoryResponse<Any> {
        val categoryList = categoryService.getAllcategories()
         return if (categoryList!!.isEmpty()){
             CategoryResponse.ErrorResponse(error = true, message = "No categories found")
         }
        else{
            CategoryResponse.SuccessResponse(error = false, message = "categories fetched", categories = categoryList)
        }



    }


    private suspend fun iscategoryExist(catName: String):Boolean{

        return categoryService.findcategoryByName(catName)!=null
    }

}