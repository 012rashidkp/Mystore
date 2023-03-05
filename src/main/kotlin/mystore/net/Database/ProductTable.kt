package mystore.net.Database

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ProductTable : Table("Products") {
    val product_id=integer("product_id").autoIncrement()
    val product_Name=varchar("product_Name",250)
    val product_desc=varchar("product_desc",350)
    val category_id=integer("category_id").references(ref = CategoryTable.category_id, onDelete = ReferenceOption.CASCADE)
    val marked_price=double("marked_price")
    val selling_price=double("selling_price")
    val product_image=blob("product_image")
    val created_at=datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey=PrimaryKey(product_id)
}