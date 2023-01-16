package mystore.net.Service

import mystore.net.Database.DatabaseFactory.dbQuery
import mystore.net.Database.UserTable
import mystore.net.Database.touser
import mystore.net.Model.Users
import mystore.net.Requests.CreateuserParams
import mystore.net.Security.hash
import org.jetbrains.exposed.sql.and
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
       // return RowToUser(statement?.resultedValues?.get(0))
        return statement?.resultedValues?.get(0).touser()
    }

    override suspend fun Loginuser(email: String, password: String): Users? {
        val hashedpassword= hash(password)
        val useRow= dbQuery { UserTable.select { UserTable.email eq email and (UserTable.password eq hashedpassword)}.firstOrNull()  }
       return useRow.touser()
    }

    override suspend fun findUserByEmail(email: String): Users? {
        val user= dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { it.touser() }.singleOrNull()
        }
        return user
    }

    override suspend fun findUserphone(phone: String): Users? {
        val user= dbQuery {
            UserTable.select { UserTable.phone.eq(phone) }
                .map { it.touser() }.singleOrNull()
        }
        return user
    }

}

//fun checkPhoneNumberExists(phoneNumber: String): Boolean {
//    val result = transaction {
//        PhoneNumber.find { PhoneNumbers.number eq phoneNumber }.firstOrNull()
//    }
//    return result != null
//}
