package com.carvalho.todo.api


import com.carvalho.todo.model.Categoria
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    // Método GET resgatado da API Online
    @GET("categoria")
    suspend fun listaCategoria(): Response<List<Categoria>>

}