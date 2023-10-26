package mystore.net.Service

import mystore.net.Database.CategoryTable
import mystore.net.Database.DatabaseFactory.dbQuery
import mystore.net.Database.ProductTable
import mystore.net.Database.tocategory
import mystore.net.Database.toproduct
import mystore.net.Model.Categories
import mystore.net.Model.Products
import mystore.net.Requests.CreateProductParams
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class ProductServiceImpl : ProductService {
    override suspend fun createproduct(params: CreateProductParams): Products? {
        var statement: InsertStatement<Number>?=null
        dbQuery {
            statement = ProductTable.insert {
                it[product_Name] = params.product_Name
                it[product_desc]=params.product_desc
                it[category_id]=params.category_id
                it[marked_price]=params.marked_price
                it[selling_price]=params.selling_price
                it[product_image]=params.product_image

            }
        }
        return statement?.resultedValues?.get(0).toproduct()
    }

    override suspend fun getproductName(productName: String): Products? {
        val product= dbQuery {
            ProductTable.select { ProductTable.product_Name.eq(productName) }
                .map { it.toproduct() }.singleOrNull()
        }
        return product
    }

    override suspend fun findCategorybyId(cat_id: Int): Categories? {
        val category= dbQuery {
            CategoryTable.select { CategoryTable.category_id.eq(cat_id) }
                .map { it.tocategory() }.singleOrNull()
        }
        return category
    }
}