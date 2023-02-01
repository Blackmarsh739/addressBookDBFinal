import com.addressbook.tables.*
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

fun connectToDatabase(): Database {
    return Database.connect(
        HikariDataSource().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/addressBookDatabase"
            username = "parthraval73"
            password = "password"
            isAutoCommit = false
            maximumPoolSize = 5
        }
    )

    val schema = listOf<Table>(
        PersonsTable,
        PhoneNumbersTable,
        EmailsTable,
        AddressesTable,
        GroupsTable,
        GroupContactAssociationTable
    )

    fun resetDatabase(){
        transaction {
            schema.reversed().forEach {
                SchemaUtils.drop(it)
            }
            schema.forEach{
                SchemaUtils.create(it)
            }
        }
    }
}