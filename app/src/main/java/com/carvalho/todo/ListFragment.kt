package com.carvalho.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carvalho.todo.adapter.TarefaAdapter
import com.carvalho.todo.adapter.TaskItemClickListener
import com.carvalho.todo.databinding.FragmentListBinding
import com.carvalho.todo.model.Tarefa

class ListFragment : Fragment(), TaskItemClickListener {

    private lateinit var binding: FragmentListBinding
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Instância do binding
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        viewModel.listaTarefa()
        viewModel.listaCategoria()
        val tarefaAdapter = TarefaAdapter(this, viewModel)

        viewModel.responseTarefa.observe(viewLifecycleOwner) { reponse ->
            if (reponse != null) {
                tarefaAdapter.setLista(reponse.body()!!)
            }
        }

        viewModel.responseCategoria.observe(viewLifecycleOwner) { response ->
            Log.d("Requisicao", response.body().toString())
        }


        // Instância do adapter criado


        // Configura modo de exibição do recycler
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        // Passa para o recycle o adapter criado
        binding.recyclerView.adapter = tarefaAdapter
        // Otimiza o recycler
        binding.recyclerView.setHasFixedSize(true)
        // Manda para uma lista para o adapter
        // TarefaAdapter.setLista(listDeTarefas)


        // Pega o Evento de click do componente
        binding.floatingAdd.setOnClickListener {
            viewModel.tarefaSelec = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }


        return binding.root
    }

    // Metodo ira resgatar a tarefa selecionada
    override fun onTaskClicked(tarefa: Tarefa) {
        viewModel.tarefaSelec = tarefa
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }

}