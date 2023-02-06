package com.addressbook

import AddAddressCommand
import Commands.*
import ListAllPersonCommand
import PersonDB
import SerchContactCommand
import com.addressbook.tables.*
import com.example.addressbook.requests.*
import dbSetup.connectToDatabase
import dbSetup.resetDatabase
import entryPoints.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import storage.GroupDB
import storage.PhoneNumberDB

fun main(args: Array<String>) {

    connectToDatabase()
    resetDatabase()

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
    val g1 = addGroupEntryPoint(AddGroupRequest("Vayana")).orNull()!!
    val g2 = addGroupEntryPoint(AddGroupRequest("PDEU")).orNull()!!
    val g3 = addGroupEntryPoint(AddGroupRequest("Hostel")).orNull()!!
    val g4 = addGroupEntryPoint(AddGroupRequest("Random")).orNull()!!


    //Add Address
    addAddressEntryPoint(AddAddressRequest(bhagvat.personId,AddressType.Home,"Gondal")).orNull()!!
    addAddressEntryPoint(AddAddressRequest(parth.personId,AddressType.Home,"Bhavnagar")).orNull()!!
    addAddressEntryPoint(AddAddressRequest(hamza.personId,AddressType.Office,"Baroda")).orNull()!!
    addAddressEntryPoint(AddAddressRequest(shivam.personId,AddressType.Office,"Baroda")).orNull()!!

    //Add Phone Number
    val p1 = addPhoneNumberEntryPoint(PhoneNumberRequest(bhagvat.personId, PhoneNumberType.Home,"123")).orNull()!!
    val p2 = addPhoneNumberEntryPoint( PhoneNumberRequest(parth.personId, PhoneNumberType.Office,"456")).orNull()!!
    val p3 = addPhoneNumberEntryPoint(PhoneNumberRequest(hamza.personId, PhoneNumberType.Home,"789")).orNull()!!
    val p4 = addPhoneNumberEntryPoint( PhoneNumberRequest(shivam.personId, PhoneNumberType.Office,"100")).orNull()!!

    //Add Email
    addEmailEntryPoint(EmailRequest(parth.personId, EmailType.Office, "parth.raval@vyana.com")).orNull()!!
    addEmailEntryPoint(EmailRequest(bhagvat.personId, EmailType.Home, "bhagvat.j@home.com")).orNull()!!
    addEmailEntryPoint(EmailRequest(hamza.personId, EmailType.Office, "hamza.malik@vyana.com")).orNull()!!
    addEmailEntryPoint(EmailRequest(shivam.personId, EmailType.Home, "shivam.chavda@home.com")).orNull()!!

    //Update Person name
    updatePersonEntryPoint(UpdatePersonRequest(parth.personId,"Black","Marsh"))

    //Update phone number
    updatePhoneNumberEntryPoint(UpdatePhoneNumberRequest(p1.personId, "007", parth.personId, p1.phoneNumberType))

    //add contact inside groups
    addContactInGroupEntryPoint(g1.groupId, listOf(parth.personId, hamza.personId, shivam.personId)).orNull()!!
    addContactInGroupEntryPoint(g2.groupId, listOf(parth.personId, hamza.personId, bhagvat.personId)).orNull()!!
    addContactInGroupEntryPoint(g3.groupId, listOf(parth.personId, bhagvat.personId)).orNull()!!
    addContactInGroupEntryPoint(g4.groupId, listOf(hamza.personId)).orNull()!!

    //update groups
    updateGroupEntryPoint(UpdateGroupRequest(g1.groupId, "Vayana Network")).orNull()!!

    //remove person
    removePersonEntryPoint(shivam.personId)

    //delete groups
    removeGroupEntryPoint(g4.groupId ).orNull()!!

    //List of persons
    val ListOfPersons = (ListAllPersonCommand(PersonDB).execute())
    println(ListOfPersons)

    println("\n")

    //List of phone number
    val ListOfPhoneNumber = (ListAllPhoneNumberCommand(PhoneNumberDB).execute())
    println(ListOfPhoneNumber)

    println("\n")

    //List of group names
    val ListOfGroups = (ListAllGroupCommand().execute())
    println(ListOfGroups)



    println("\n")
    println(SerchContactCommand(PersonDB, "Hamza").execute())
}
