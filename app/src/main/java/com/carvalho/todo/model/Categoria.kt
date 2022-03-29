package com.carvalho.todo.model

data class Categoria(
    val id: Long,
    var descricao: String?,
    var tarefas: List<Tarefa>?
) {
    override fun toString(): String {
        //toString para exibir apenas a descrição da categoria (Não nula)
        return descricao!!
    }
}