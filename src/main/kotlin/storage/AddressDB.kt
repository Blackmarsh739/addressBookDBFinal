package com.addressbook.storages

import com.addressbook.tables.Addresses
import com.example.addressbook.Address
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object AddressDB {

    fun addAddress(address: Address): Address {
        transaction {
            Addresses.insert{
                it[this.addressId] = address.addressId
                it[this.personId] = address.personId
                it[this.addressType] = address.addressType
                it[this.addressDetail] = address.addressLine
            }
        }
        return address
    }
}