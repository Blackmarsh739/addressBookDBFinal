package Commands

import com.addressbook.commands.Command
import com.example.addressbook.Email
import com.example.addressbook.requests.EmailRequest
import storage.EmailDB
import java.util.*

fun EmailRequest.toEmail() =
    Email(
    emailId = UUID.randomUUID(),
        personId = this@toEmail.personId,
        emailType = this@toEmail.type,
        emailDetail = this@toEmail.emailDetail
    )

class AddEmailCommand(
    private val storage: EmailDB,
    private val request: EmailRequest
): Command {
    override fun execute(): Any {
        val email = request.toEmail()
        val emailDetail = EmailDB.addEmail(email)

        return Email(
            emailId = emailDetail.emailId,
            personId = emailDetail.personId,
            emailType = emailDetail.emailType,
            emailDetail = emailDetail.emailDetail
        )
    }

}