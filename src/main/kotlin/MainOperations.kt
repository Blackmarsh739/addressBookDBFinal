package com.addressbook

import AddAddressCommand
import AddPersonCommand
import Commands.AddEmailCommand
import Commands.AddPhoneNumberCommand
import PersonDB
import UpdatePersonCommand
import com.addressbook.storages.AddressDB
import com.addressbook.tables.Addresses
import com.addressbook.tables.Emails
import com.addressbook.tables.Persons
import com.addressbook.tables.PhoneNumbers
import com.example.addressbook.requests.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import storage.EmailDB
import storage.PhoneDB

fun main(args: Array<String>) {
    val url = "jdbc:mysql://localhost:3306/addressBookDatabase"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "parthraval73"
    val password = "password"

    Database.connect(url, driver, username, password)

    transaction {
        SchemaUtils.drop(Persons, PhoneNumbers, Emails, Addresses)
        SchemaUtils.create(Persons, PhoneNumbers, Emails, Addresses)
    }

    val person1 = AddPersonRequest("Parth","Raval")
    val parth = AddPersonCommand(PersonDB, person1).execute()
    val person2 = AddPersonRequest("Hamza","Malik")
    val hamza = AddPersonCommand(PersonDB, person2).execute()
    val person3 = AddPersonRequest("Bhagvat","Jadeja")
    val bhagvat = AddPersonCommand(PersonDB, person3).execute()

    AddAddressCommand(AddressDB, AddAddressRequest(bhagvat.personId,AddressType.Office,"Baroda")).execute()
    AddPhoneNumberCommand(PhoneDB, PhoneNumberRequest(parth.personId, PhoneNumberType.Home,"7031013939")).execute()
    AddEmailCommand(EmailDB, EmailRequest(parth.personId, EmailType.Home, "parth.raval@vyana.com")).execute()
    UpdatePersonCommand(PersonDB,UpdatePersonRequest(parth.personId,"Black","Marsh")).execute()

}
