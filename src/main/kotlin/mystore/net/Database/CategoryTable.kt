package mystore.net.Database


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object CategoryTable : Table("Categories") {
    val category_id=integer("category_id").autoIncrement()
    val categoryName=varchar("categoryName",250)
    val category_image=varchar("category_image",255)
    val created_at=datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey=PrimaryKey(category_id)
}

