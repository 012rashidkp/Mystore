package mystore.net.Database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object BannerTable : Table("Banners") {
    val banner_id=integer("banner_id").autoIncrement()
    val title=varchar("title",250)
    val description=varchar("description",350)
    val banner_image=blob("banner_image")
    val created_at=datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey=PrimaryKey(banner_id)

}