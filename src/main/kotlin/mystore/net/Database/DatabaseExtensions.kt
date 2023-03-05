package mystore.net.Database

import mystore.net.Model.Banners
import mystore.net.Model.Categories
import mystore.net.Model.Products
import mystore.net.Model.Users
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.touser():Users?{
    return if (this==null)null
    else Users(
        userid = this[UserTable.userid],
        username = this[UserTable.username],
        email = this[UserTable.email],
        phone = this[UserTable.phone],
        city = this[UserTable.city],
        createdAt = this[UserTable.createdAt].toString(),
        is_superuser = this[UserTable.is_superuser]
    )
}

fun ResultRow?.tobanner():Banners?{
    return if (this == null) null
    else Banners(
        banner_id = this[BannerTable.banner_id],
        title = this[BannerTable.title],
        description = this[BannerTable.description],
        banner_image = this[BannerTable.banner_image],
        created_at = this[BannerTable.created_at].toString()
    )

}
fun ResultRow?.tocategory():Categories?{
    return if (this == null) null
    else Categories(
        category_id = this[CategoryTable.category_id],
        categoryName = this[CategoryTable.categoryName],
        category_image = this[CategoryTable.category_image],
        created_at = this[CategoryTable.created_at].toString()
    )
}
fun ResultRow?.toproduct():Products?{
    return if (this == null) null
    else Products(
        product_id = this[ProductTable.product_id],
        product_Name = this[ProductTable.product_Name],
        product_desc = this[ProductTable.product_desc],
        category_id = this[ProductTable.category_id],
        marked_price = this[ProductTable.marked_price],
        selling_price = this[ProductTable.selling_price],
        product_image = this[ProductTable.product_image],
        created_at = this[ProductTable.created_at].toString()
    )

}
fun ResultRow?.toproductJoinwithCategory():Products?{
    return if (this == null) null
    else Products(
        product_id = this[ProductTable.product_id],
        product_Name = this[ProductTable.product_Name],
        product_desc = this[ProductTable.product_desc],
        category_id = this[ProductTable.category_id],
        categories = Categories(
            category_id = this[CategoryTable.category_id],
            categoryName = this[CategoryTable.categoryName],
            category_image = this[CategoryTable.category_image],
            created_at = this[CategoryTable.created_at].toString()
        ),
        marked_price = this[ProductTable.marked_price],
        selling_price = this[ProductTable.selling_price],
        product_image = this[ProductTable.product_image],
        created_at = this[ProductTable.created_at].toString()

    )
}