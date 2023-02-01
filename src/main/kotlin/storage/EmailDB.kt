package storage

import arrow.core.Either
import com.example.addressbook.Email
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import com.addressbook.tables.EmailsTable
import com.example.addressbook.Person
import com.example.addressbook.requests.EmailRequest
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toEmail() = Email(
    personId = this@toEmail[EmailsTable.personId],
    emailDetail = this@toEmail[EmailsTable.email],
    emailId = this@toEmail[EmailsTable.emailId],
    emailType = this@toEmail[EmailsTable.emailType]
)
object EmailDB {

    fun addEmail(email: EmailRequest): Either<Exception, Email> {
      return try {
           val res = transaction {

               EmailsTable.insert {
                   it[this.personId] = email.personId
                   it[this.emailType] = email.type
                   it[this.email] = email.emailDetail
               }
           }.resultedValues!!.first().toEmail()
           Either.Right(res)
       } catch(e: Exception){
           Either.Left(Exception("There was some error."))
       }
    }
}