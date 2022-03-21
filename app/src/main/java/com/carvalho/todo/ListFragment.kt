package com.carvalho.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carvalho.todo.adapter.TarefaAdapter
import com.carvalho.todo.model.Tarefa

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val listDeTarefas = mutableListOf(
            Tarefa(
                "Lavar o Carro",
                "Limpar parte de fora do carro",
                "Pedro",
                "21-03-2020",
                true,
                "Dia-a-Dia"
            ),
            Tarefa(
                "Lavar a Roupa",
                "Lavar todas as roupas do certo",
                "Pedro",
                "22-03-2020",
                true,
                "Dia-a-Dia"
            )
        )

        val recyclerTarefas = view.findViewById<RecyclerView>(R.id.recyclerView)
        val tarefaAdapter = TarefaAdapter()

        //configura modo de exebiçao do recycler
        recyclerTarefas.layoutManager = LinearLayoutManager(context)
        // passa para o recycle o adapter criado
        recyclerTarefas.adapter = tarefaAdapter
        // otimiza o recycler
        recyclerTarefas.setHasFixedSize(true)
        // manda para o adapter uma lista
        tarefaAdapter.setLista(listDeTarefas)

        return view
    }

}