package com.example.gestiontaller.services

import com.example.gestiontaller.model.Accident
import com.example.gestiontaller.model.Client
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET

interface ApiService {

    @GET("/api/Accident")
    suspend fun getAccidents(): Response<ArrayList<Accident>>

    @DELETE
    suspend fun deleteAccident(id: Int): Response<Boolean>
}