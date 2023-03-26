package mystore.net.Service

import mystore.net.Database.*
import mystore.net.Database.DatabaseFactory.dbQuery
import mystore.net.Model.Categories
import mystore.net.Requests.CreateCategoryparams
import mystore.net.Requests.UpdateCategoryParams
import org.jetbrains.exposed.sql.*

import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement


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

    override suspend fun findCategoryId(cat_id: Int): Categories? {
        val category= dbQuery {
            CategoryTable.select { CategoryTable.category_id.eq(cat_id) }
                .map { it.tocategory() }.singleOrNull()
        }
        return category
    }

    override suspend fun deletecategory(category_id: Int): Boolean? {
        return dbQuery {
            CategoryTable.deleteWhere { CategoryTable.category_id eq category_id } > 0
        }
    }

    override suspend fun UpdateCategory(params: UpdateCategoryParams): Boolean? {
        var result = -1
        dbQuery {
            result = CategoryTable.update({ CategoryTable.category_id eq params.category_id }) {
                it[categoryName] = params.categoryName
                it[category_image] = params.categoryImage

            }
        }
        return result == 1
    }


}