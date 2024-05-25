package com.example.gestiontaller.services

import com.example.gestiontaller.model.Accident
import com.example.gestiontaller.model.AccidentDto
import com.example.gestiontaller.model.Client
import com.example.gestiontaller.model.ClientDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    @GET("/api/Accident")
    suspend fun getAccidents(): Response<ArrayList<Accident>>

    @DELETE("/api/Accident")
    suspend fun deleteAccident(@Query("id") idAccident: Int): Response<Boolean>

    @POST("/api/Accident")
    suspend fun addAccident(@Body accident: AccidentDto): Response<Accident>

    @PUT("/api/Accident")
    suspend fun editAccident(
        @Query("id") idAccident: Int,
        @Body accident: AccidentDto
    ): Response<Accident>

    @GET("/api/Client")
    suspend fun getClients(): Response<ArrayList<Client>>

    @DELETE("/api/Client")
    suspend fun deleteClient(@Query("id") idClient: Int): Response<Boolean>

    @POST("/api/Client")
    suspend fun addClient(@Body client: ClientDto): Response<Client>

    @PUT("/api/Client")
    suspend fun editClient(@Query("id") idClient: Int, @Body client: ClientDto): Response<Client>
}