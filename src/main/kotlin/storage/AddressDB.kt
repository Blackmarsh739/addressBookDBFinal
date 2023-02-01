package com.addressbook.storages

import arrow.core.Either
import com.addressbook.tables.AddressesTable
import com.example.addressbook.Address
import com.example.addressbook.Person
import com.example.addressbook.requests.AddAddressRequest
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction


fun ResultRow.toAddress() = Address(
    personId = this@toAddress[AddressesTable.personId],
    addressLine = this@toAddress[AddressesTable.addressDetail],
    addressId = this@toAddress[AddressesTable.addressId],
    addressType = this@toAddress[AddressesTable.addressType]
)
object AddressDB {

    fun addAddress(address: AddAddressRequest): Either<Exception, Address> {
       return try{
            val res = transaction {
            AddressesTable.insert{
                it[this.personId] = address.personId
                it[this.addressType] = address.type
                it[this.addressDetail] = address.addressDetail
            }
        }.resultedValues!!.first().toAddress()
            Either.Right(res)
    }
        catch(e: Exception){
            Either.Left(Exception("There was some error."))
        }
    }

}