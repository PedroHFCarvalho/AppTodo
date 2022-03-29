package com.carvalho.todo.repository

import com.carvalho.todo.api.RetrofitInstance
import com.carvalho.todo.model.Categoria
import com.carvalho.todo.model.Tarefa
import retrofit2.Response

class Repository {

    // Método que acessa a API através do RetrofitInstance e chama o GET da ApiService
    suspend fun listaCategoria(): Response<List<Categoria>> {
        return RetrofitInstance.api.listaCategoria()
    }

    // Método que acessa a API através do RetrofitInstance e chama o POST da ApiService
    suspend fun addTarefa(tarefa: Tarefa): Response<Tarefa> {
        return RetrofitInstance.api.addTarefa(tarefa)
    }
    // Método que acessa a API através do RetrofitInstance e chama o GET da ApiService
    suspend fun listaTarefas(): Response<List<Tarefa>> {
        return RetrofitInstance.api.listaTarefas()
    }

}