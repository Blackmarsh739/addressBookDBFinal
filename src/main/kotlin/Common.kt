import com.addressbook.tables.*
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun setDatabase(){
    connectToDatabase()
    resetDatabase()
}
internal fun connectToDatabase(): Database {
    return Database.connect(
        HikariDataSource().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/addressBookDatabase"
            username = "parthraval73"
            password = "password"
            isAutoCommit = false
            maximumPoolSize = 5
        }
    )
}

internal fun resetDatabase(){
    transaction {
        SchemaUtils.drop(
            PersonsTable, PhoneNumbersTable, EmailsTable, AddressesTable, GroupsTable,
            GroupContactAssociationTable
        )
        SchemaUtils.create(
            PersonsTable, PhoneNumbersTable, EmailsTable, AddressesTable, GroupsTable,
            GroupContactAssociationTable
        )
    }
}