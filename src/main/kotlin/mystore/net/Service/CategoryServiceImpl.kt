package mystore.net.Service

import mystore.net.Database.*
import mystore.net.Database.DatabaseFactory.dbQuery
import mystore.net.Model.Categories
import mystore.net.Requests.CreateCategoryparams

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement


class CategoryServiceImpl : CategoryService {
    override suspend fun Createcategory(params: CreateCategoryparams): Categories? {
       var statement:InsertStatement<Number>?=null
        dbQuery {
            statement = CategoryTable.insert {
                it[categoryName] = params.categoryName
                it[category_image]=params.categoryImage

            }
        }

        return statement?.resultedValues?.get(0).tocategory()
    }

    override suspend fun findcategoryByName(catName: String): Categories? {
        val category= dbQuery {
            CategoryTable.select { CategoryTable.categoryName.eq(catName) }
                .map { it.tocategory() }.singleOrNull()
        }
        return category
    }

    override suspend fun getAllcategories(): List<Categories?> {
        return dbQuery {
            CategoryTable.selectAll().map { it.tocategory() }
        }
    }
}