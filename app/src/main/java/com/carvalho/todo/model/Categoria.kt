package com.carvalho.todo.model

data class Categoria(
    val id: Long,
    var descricao: String,
    var tarefa: List<Tarefa>
) {
}