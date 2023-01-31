package com.addressbook

import AddAddressCommand
import AddPersonCommand
import Commands.*
import ListAllPersonCommand
import PersonDB
import RemovePersonCommand
import SerchContactCommand
import UpdatePersonCommand
import com.addressbook.storages.AddressDB
import com.addressbook.tables.*
import com.example.addressbook.requests.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import storage.EmailDB
import storage.GroupDB
import storage.PhoneNumberDB

fun main(args: Array<String>) {
    val url = "jdbc:mysql://localhost:3306/addressBookDatabase"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "parthraval73"
    val password = "password"

    Database.connect(url, driver, username, password)

    transaction {
        SchemaUtils.drop(PersonsTable, PhoneNumbersTable, EmailsTable, AddressesTable, GroupsTable,GroupContactAssociationTable)
        SchemaUtils.create(PersonsTable, PhoneNumbersTable, EmailsTable, AddressesTable, GroupsTable,GroupContactAssociationTable)
    }

    //creat persons
    val person1 = AddPersonRequest("Parth","Raval")
    val parth = AddPersonCommand(PersonDB, person1).execute()

    val person2 = AddPersonRequest("Hamza","Malik")
    val hamza = AddPersonCommand(PersonDB, person2).execute()

    val person3 = AddPersonRequest("Bhagvat","Jadeja")
    val bhagvat = AddPersonCommand(PersonDB, person3).execute()

    val person4 = AddPersonRequest("Shivam","Chavda")
    val shivam = AddPersonCommand(PersonDB, person4).execute()

    //creat groups
    val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute())
    val g2 = (AddGroupCommand(AddGroupRequest("PDEU")).execute())
    val g3 = (AddGroupCommand(AddGroupRequest("Hostel")).execute())
    val g4 = (AddGroupCommand(AddGroupRequest("Random")).execute())


    //Add Address
    AddAddressCommand(AddressDB, AddAddressRequest(bhagvat.personId,AddressType.Home,"Gondal")).execute()
    AddAddressCommand(AddressDB, AddAddressRequest(parth.personId,AddressType.Home,"Bhavnagar")).execute()
    AddAddressCommand(AddressDB, AddAddressRequest(hamza.personId,AddressType.Office,"Baroda")).execute()
    AddAddressCommand(AddressDB, AddAddressRequest(shivam.personId,AddressType.Office,"Baroda")).execute()

    //Add Phone Number
    val p1 = AddPhoneNumberCommand(PhoneNumberDB, PhoneNumberRequest(bhagvat.personId, PhoneNumberType.Home,"123")).execute()
    val p2 = AddPhoneNumberCommand(PhoneNumberDB, PhoneNumberRequest(parth.personId, PhoneNumberType.Office,"456")).execute()
    val p3 = AddPhoneNumberCommand(PhoneNumberDB, PhoneNumberRequest(hamza.personId, PhoneNumberType.Home,"789")).execute()
    val p4 = AddPhoneNumberCommand(PhoneNumberDB, PhoneNumberRequest(shivam.personId, PhoneNumberType.Office,"100")).execute()

    //Add Email
    AddEmailCommand(EmailDB, EmailRequest(parth.personId, EmailType.Office, "parth.raval@vyana.com")).execute()
    AddEmailCommand(EmailDB, EmailRequest(bhagvat.personId, EmailType.Home, "bhagvat.j@home.com")).execute()
    AddEmailCommand(EmailDB, EmailRequest(hamza.personId, EmailType.Office, "hamza.malik@vyana.com")).execute()
    AddEmailCommand(EmailDB, EmailRequest(shivam.personId, EmailType.Home, "shivam.chavda@home.com")).execute()

    //Update Person name
    UpdatePersonCommand(PersonDB,UpdatePersonRequest(parth.personId,"Black","Marsh")).execute()

    //Update phone number
    UpdatePhoneNumberCommand(PhoneNumberDB, UpdatePhoneNumberRequest((p1.phoneNumberId), "007", parth.personId, p1.phoneNumberType)).execute()

    //add contact inside groups
    AddContactInGroupCommand(g1.groupId, listOf(parth.personId, hamza.personId, shivam.personId)).execute()
    AddContactInGroupCommand(g2.groupId, listOf(parth.personId, hamza.personId, bhagvat.personId)).execute()
    AddContactInGroupCommand(g3.groupId, listOf(parth.personId, bhagvat.personId)).execute()
    AddContactInGroupCommand(g4.groupId, listOf(hamza.personId)).execute()

    //update groups
    UpdateGroupCommand(GroupDB, UpdateGroupRequest(g1.groupId, "Vayana Network")).execute()

    //remove person
    RemovePersonCommand(PersonDB, shivam.personId).execute()

    //delete groups
    RemoveGroupCommand(GroupDB, g4.groupId ).execute()

    //List of persons
    val ListOfPersons = (ListAllPersonCommand(PersonDB).execute())
    ListOfPersons.forEach {
        println(it)
    }

    println("\n")

    //List of phone number
    val ListOfPhoneNumber = (ListAllPhoneNumberCommand(PhoneNumberDB).execute())
    ListOfPhoneNumber.forEach {
        println(it)
    }

    println("\n")

    //List of group names
    val ListOfGroups = (ListAllGroupCommand(GroupDB).execute())
    ListOfGroups.forEach {
        println(it)
    }

    println("\n")
    println(SerchContactCommand(PersonDB, "Hamza").execute())
}
