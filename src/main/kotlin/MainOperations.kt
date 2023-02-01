package com.addressbook

import AddAddressCommand
import Commands.*
import ListAllPersonCommand
import PersonDB
import SerchContactCommand
import com.addressbook.tables.*
import com.example.addressbook.requests.*
import entryPoints.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
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
    val parth = addPersonEntryPoint(person1).orNull()!!

    val person2 = AddPersonRequest("Hamza","Malik")
    val hamza = addPersonEntryPoint(person2).orNull()!!

    val person3 = AddPersonRequest("Bhagvat","Jadeja")
    val bhagvat = addPersonEntryPoint(person3).orNull()!!

    val person4 = AddPersonRequest("Shivam","Chavda")
    val shivam = addPersonEntryPoint(person4).orNull()!!

    //creat groups
    val g1 = (AddGroupCommand(AddGroupRequest("Vayana")).execute())
    val g2 = (AddGroupCommand(AddGroupRequest("PDEU")).execute())
    val g3 = (AddGroupCommand(AddGroupRequest("Hostel")).execute())
    val g4 = (AddGroupCommand(AddGroupRequest("Random")).execute())


    //Add Address
    addAddressEntryPoint(AddAddressRequest(bhagvat.personId,AddressType.Home,"Gondal")).orNull()!!
    addAddressEntryPoint(AddAddressRequest(parth.personId,AddressType.Home,"Bhavnagar")).orNull()!!
    addAddressEntryPoint(AddAddressRequest(hamza.personId,AddressType.Office,"Baroda")).orNull()!!
    addAddressEntryPoint(AddAddressRequest(shivam.personId,AddressType.Office,"Baroda")).orNull()!!

    //Add Phone Number
    val p1 = AddPhoneNumberCommand(PhoneNumberRequest(bhagvat.personId, PhoneNumberType.Home,"123")).execute().orNull()!!
    val p2 = AddPhoneNumberCommand( PhoneNumberRequest(parth.personId, PhoneNumberType.Office,"456")).execute().orNull()!!
    val p3 = AddPhoneNumberCommand(PhoneNumberRequest(hamza.personId, PhoneNumberType.Home,"789")).execute().orNull()!!
    val p4 = AddPhoneNumberCommand( PhoneNumberRequest(shivam.personId, PhoneNumberType.Office,"100")).execute().orNull()!!

    //Add Email
    addEmailEntryPoint(EmailRequest(parth.personId, EmailType.Office, "parth.raval@vyana.com")).orNull()!!
    addEmailEntryPoint(EmailRequest(bhagvat.personId, EmailType.Home, "bhagvat.j@home.com")).orNull()!!
    addEmailEntryPoint(EmailRequest(hamza.personId, EmailType.Office, "hamza.malik@vyana.com")).orNull()!!
    addEmailEntryPoint(EmailRequest(shivam.personId, EmailType.Home, "shivam.chavda@home.com")).orNull()!!

    //Update Person name
    updatePersonEntryPoint(UpdatePersonRequest(parth.personId,"Black","Marsh"))

    //Update phone number
    UpdatePhoneNumberCommand(UpdatePhoneNumberRequest(p1.personId, "007", parth.personId, p1.phoneNumberType)).execute()

    //add contact inside groups
    AddContactInGroupCommand(g1.groupId, listOf(parth.personId, hamza.personId, shivam.personId)).execute()
    AddContactInGroupCommand(g2.groupId, listOf(parth.personId, hamza.personId, bhagvat.personId)).execute()
    AddContactInGroupCommand(g3.groupId, listOf(parth.personId, bhagvat.personId)).execute()
    AddContactInGroupCommand(g4.groupId, listOf(hamza.personId)).execute()

    //update groups
    UpdateGroupCommand(GroupDB, UpdateGroupRequest(g1.groupId, "Vayana Network")).execute()

    //remove person
    removePersonEntryPoint(shivam.personId)

    //delete groups
    RemoveGroupCommand(GroupDB, g4.groupId ).execute()

    //List of persons
    val ListOfPersons = (ListAllPersonCommand(PersonDB).execute())
    println(ListOfPersons)

    println("\n")

    //List of phone number
    val ListOfPhoneNumber = (ListAllPhoneNumberCommand(PhoneNumberDB).execute())
    println(ListOfPhoneNumber)

    println("\n")

    //List of group names
    val ListOfGroups = (ListAllGroupCommand(GroupDB).execute())
    ListOfGroups.forEach {
        println(it)
    }

    println("\n")
    println(SerchContactCommand(PersonDB, "Hamza").execute())
}
