package com.example.gestiontaller.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val BASE_URL = "http://10.0.2.2:5276"

    //Creamos un objeto Retrofit para realizar peticiones a la API
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiService: ApiService by lazy {
        getRetrofitInstance().create(ApiService::class.java)
    }
}