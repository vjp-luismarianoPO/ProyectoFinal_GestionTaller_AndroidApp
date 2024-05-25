package com.example.gestiontaller.model

data class Accident(
    val clientId: Int,
    val companyId: Int,
    val date: String,
    val id: Int,
    val supplierId: Int,
    val totalAmount: Double,
    val type: String
)

data class AccidentDto(
    val clientId: Int,
    val companyId: Int,
    val supplierId: Int,
    val totalAmount: Double,
    val type: String
)