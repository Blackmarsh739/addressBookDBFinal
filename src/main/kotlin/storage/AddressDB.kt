package com.addressbook.storages

import com.addressbook.tables.AddressesTable
import com.example.addressbook.Address
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

    fun addAddress(address: AddAddressRequest): Address {
        val res = transaction {
            AddressesTable.insert{
                it[this.personId] = address.personId
                it[this.addressType] = address.type
                it[this.addressDetail] = address.addressDetail
            }
        }.resultedValues!!.first().toAddress()

        return res
    }
}