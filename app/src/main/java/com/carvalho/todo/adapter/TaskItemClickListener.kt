package com.carvalho.todo.adapter

import com.carvalho.todo.model.Tarefa

interface TaskItemClickListener {

    // Metodo ira resgatar a tarefa selecionada
    fun onTaskClicked(tarefa: Tarefa)
}