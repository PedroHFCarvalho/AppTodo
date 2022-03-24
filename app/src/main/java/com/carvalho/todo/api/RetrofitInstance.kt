package com.carvalho.todo.api

import com.carvalho.todo.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Criando Variáveis para instância da API Online
    private val retrofit by lazy { // Lazy executa o bloco de código assim que a for iniciada
        // Passamos a URL da API e Convertemos o Json para o kotlin ler
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    //Cria a instância com base na Interface ApiService
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}