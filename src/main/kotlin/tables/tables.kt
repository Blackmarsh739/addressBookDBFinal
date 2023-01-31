package com.addressbook.tables

import com.example.addressbook.requests.AddressType
import com.example.addressbook.requests.EmailType
import com.example.addressbook.requests.PhoneNumberType
import org.jetbrains.exposed.sql.Table

object PersonsTable : Table() {

    val personId = uuid("person_id").autoGenerate()
    val firstName = varchar("first_name", length = 100)
    val lastName = varchar("last_name", length = 100)

    override val primaryKey = PrimaryKey(personId, name = "PK_Contact_ID")
}

object PhoneNumbersTable : Table() {
    val phoneNumberId = uuid("phone_number_id").autoGenerate()
    val personId = (uuid("person_id") references PersonsTable.personId).index()
    val phoneNumberType =   enumerationByName<PhoneNumberType>("type",10)
    val phone = varchar("phone_number", length = 100)

    override val primaryKey = PrimaryKey(PhoneNumbersTable.phoneNumberId, name = "PK_PhoneNumber_ID")
}

object EmailsTable : Table() {
    val emailId = uuid("email_id").autoGenerate()
    val personId = (uuid("person_id") references PersonsTable.personId).index()
    val emailType =   enumerationByName<EmailType>("type",10)
    val email = varchar("email", length = 100)

    override val primaryKey = PrimaryKey(EmailsTable.emailId, name = "PK_Email_ID")
}

object AddressesTable : Table() {
    val addressId = uuid("address_id").autoGenerate()
    val personId = (uuid("person_id") references PersonsTable.personId).index()
    val addressType =   enumerationByName<AddressType>("type",10)
    val addressDetail = varchar("address_detail", length = 100)


    override val primaryKey = PrimaryKey(AddressesTable.addressId, name = "PK_Address_ID")
//    override val foreignKey = ForeignKey(Addresses.personId, name = "FK_Person_ID")
}
object GroupsTable : Table("groupsTable") {
    val groupId = uuid("group_id").autoGenerate()
//    val personId = (uuid("person_id") references Persons.personId).index()
    val groupName = varchar("group_name", length = 100)

    override val primaryKey = PrimaryKey(groupId, name = "PK_Groups_ID")
}

object GroupContactAssociationTable: Table() {
    val groupId = uuid("group_id").references(GroupsTable.groupId)
    val personId = uuid("person_id").references(PersonsTable.personId)
}