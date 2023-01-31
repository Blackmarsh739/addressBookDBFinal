package Commands

import com.addressbook.commands.Command
import com.example.addressbook.Email
import com.example.addressbook.requests.EmailRequest
import storage.EmailDB

class AddEmailCommand(
    private val storage: EmailDB,
    private val request: EmailRequest
): Command {
    override fun execute(): Email {

        return EmailDB.addEmail(request)
    }
}