package mystore.net.Database

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