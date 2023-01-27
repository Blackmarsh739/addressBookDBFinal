package storage

import com.example.addressbook.Email
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import com.addressbook.tables.Emails

object EmailDB {

    fun addEmail(email: Email): Email {
        transaction {
            Emails.insert {
                it[this.emailId] = email.emailId
                it[this.personId] = email.personId
                it[this.emailType] = email.emailType
                it[this.email] = email.emailDetail
            }
        }
        return email
    }






}