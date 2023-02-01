package Commands

import arrow.core.Either
import com.addressbook.commands.Command
import com.example.addressbook.Email
import com.example.addressbook.requests.EmailRequest
import storage.EmailDB

class AddEmailCommand(
    private val request: EmailRequest
): Command {
    override fun execute(): Either<Exception, Email> {
        return EmailDB.addEmail(request)
    }
}