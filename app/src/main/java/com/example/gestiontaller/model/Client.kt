package com.example.gestiontaller.model

data class Client(
    val accidentId: Any,
    val carModel: String,
    val companyId: Any,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String
)

data class ClientDto(
    val accidentId: Any,
    val carModel: String,
    val companyId: Any,
    val email: String,
    val name: String,
    val phone: String
)