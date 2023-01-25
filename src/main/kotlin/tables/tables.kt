package com.addressbook.tables

import com.example.addressbook.requests.AddressType
import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object Persons : Table() {
    val personId = uuid("person_id").autoGenerate()
    val firstName = varchar("first_name", length = 100)
    val lastName = varchar("last_name", length = 100)

    override val primaryKey = PrimaryKey(personId, name = "PK_Contact_ID")
}

object PhoneNumbers : Table() {
    val phoneNumberId = uuid("phone_number_id").autoGenerate()
    val personId = (uuid("person_id") references Persons.personId).index()
    val phoneNumberType = varchar("phone_number_type", length = 100)
    val phone = varchar("phone_number", length = 100)

    override val primaryKey = PrimaryKey(PhoneNumbers.phoneNumberId, name = "PK_PhoneNumber_ID")
}

object Emails : Table() {
    val emailId = uuid("email_id").autoGenerate()
    val personId = (uuid("person_id") references Persons.personId).index()
    val emailType = varchar("email_type", length = 100)
    val email = varchar("email", length = 100)

    override val primaryKey = PrimaryKey(Emails.emailId, name = "PK_Email_ID")
}

object Addresses : Table() {
    val addressId = uuid("address_id").autoGenerate()
    val personId = (uuid("person_id") references Persons.personId).index()
    val addressType =   enumerationByName<AddressType>("type",10)
    val addressDetail = varchar("address_detail", length = 100)


    override val primaryKey = PrimaryKey(Addresses.addressId, name = "PK_Address_ID")
//    override val foreignKey = ForeignKey(Addresses.personId, name = "FK_Person_ID")
}