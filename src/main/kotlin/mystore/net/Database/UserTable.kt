package mystore.net.Database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Integers
import java.time.LocalDateTime

object UserTable:Table("users") {
    val userid=integer("userid").autoIncrement()
    val username=varchar("username",250)
    val email=varchar("email",250)
    val phone=varchar("phone",250)
    val city=varchar("city",250)
    val password=text("password")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val is_superuser=bool("is_superuser").clientDefault { false }

    override val primaryKey=PrimaryKey(userid)


}