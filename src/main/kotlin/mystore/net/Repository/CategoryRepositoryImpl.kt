package mystore.net.Repository

import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.UpdateCategoryParams

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

                CategoryResponse.SuccessResponse(error = false, message = "Category created success")

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

    override suspend fun deletecategory(category_id: Int): CategoryResponse<Any> {
      return if (!iscategoryidExist(category_id)){
          CategoryResponse.ErrorResponse(error = true, message = "categoryid ${category_id} doesnot exist")

      }
      else{
          val categoryid=categoryService.deletecategory(category_id)
          if (categoryid!=null){
              CategoryResponse.SuccessResponse(error = false, message = "Category deleted success")

          }
          else{
              CategoryResponse.ErrorResponse(error = true, message = "seomething went wrong")

          }
      }
    }

    override suspend fun updatecategory(params: UpdateCategoryParams): CategoryResponse<Any> {
        return if (!iscategoryidExist(params.category_id)){
            CategoryResponse.ErrorResponse(error = true, message = "categoryid ${params.category_id} doesnot exist")

        }
        else{
            val category=categoryService.UpdateCategory(params)
            if (category !=null){

                CategoryResponse.SuccessResponse(error = false, message = "Category updated success")

            }
            else{
                CategoryResponse.ErrorResponse(error = true, message = "something went wrong")
            }
        }
    }


    private suspend fun iscategoryExist(catName: String):Boolean{

        return categoryService.findcategoryByName(catName)!=null
    }
    private suspend fun iscategoryidExist(category_id: Int):Boolean{
        return categoryService.findCategoryId(category_id)!=null
    }

}