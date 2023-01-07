package mystore.net.Service

import mystore.net.Database.DatabaseFactory.dbQuery
import mystore.net.Database.UserTable
import mystore.net.Model.Users
import mystore.net.Security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun Createuser(params: CreateuserParams): Users? {
        var statement:InsertStatement<Number>?=null
        dbQuery{
            statement=UserTable.insert {
                it[username]=params.username
                it[email]=params.email
                it[phone]=params.phone
                it[city]=params.city
                it[password]=hash(params.password)
                it[is_superuser]=params.is_superuser==false

            }
        }
        return RowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): Users? {
        val user= dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { RowToUser(it) }.singleOrNull()
        }
        return user
    }

    override suspend fun findUserphone(phone: String): Users? {
        val user= dbQuery {
            UserTable.select { UserTable.phone.eq(phone) }
                .map { RowToUser(it) }.singleOrNull()
        }
        return user
    }

    private fun RowToUser(row:ResultRow?):Users?{
        return if (row==null)null
        else Users(
            userid = row[UserTable.userid],
            username = row[UserTable.username],
            email = row[UserTable.email],
            phone = row[UserTable.phone],
            city = row[UserTable.city],
            createdAt = row[UserTable.createdAt].toString(),
            is_superuser = row[UserTable.is_superuser]


        )
    }
}