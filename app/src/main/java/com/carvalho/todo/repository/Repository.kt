package com.carvalho.todo.repository

import com.carvalho.todo.api.RetrofitInstance
import com.carvalho.todo.model.Categoria
import retrofit2.Response

class Repository {

    // Método que acessa a API através do RetrofitInstance e chama o GET da ApiService
    suspend fun listaCategoria(): Response<List<Categoria>> {
        return RetrofitInstance.api.listaCategoria()
    }

}