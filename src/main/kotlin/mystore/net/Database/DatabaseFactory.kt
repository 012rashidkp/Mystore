package mystore.net.Database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(){
        Database.connect(hikari())
       transaction {
           SchemaUtils.create(UserTable)
           SchemaUtils.create(BannerTable)
           SchemaUtils.create(CategoryTable)
           SchemaUtils.create(ProductTable)

       }
    }
    fun hikari():HikariDataSource{
        val config=HikariConfig()
        config.driverClassName="org.postgresql.Driver"
        config.jdbcUrl="jdbc:postgresql:Mystore?user=postgres&password=admin123"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}