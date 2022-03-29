package com.carvalho.todo.api


import com.carvalho.todo.model.Categoria
import com.carvalho.todo.model.Tarefa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Método GET resgatado da API Online
    @GET("categoria")
    suspend fun listaCategoria(): Response<List<Categoria>>

    // Método POST resgatado da API Online
    @POST("tarefa")
    suspend fun addTarefa(@Body tarefa: Tarefa): Response<Tarefa>

    //Método GET para tarefas resgatado da API Online
    @GET("tarefa")
    suspend fun listaTarefas(): Response<List<Tarefa>>
}