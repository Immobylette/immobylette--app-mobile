package com.immobylette.appmobile.data.dto

import java.util.UUID

data class ThirdPartyDto(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val surname: String = "",
    val photo: String = ""
){
    fun toFormattedString(): String {
        return "$name $surname"
    }
}
