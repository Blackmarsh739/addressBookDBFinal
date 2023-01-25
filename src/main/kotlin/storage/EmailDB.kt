package storage

import com.example.addressbook.Email
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionScope

object EmailDB {

    fun addEmail(email: Email): Email{

        transaction {
            Email.insert{
                it[this.]
            }
        }
        return
    }
}