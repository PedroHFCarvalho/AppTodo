package com.carvalho.todo

import android.os.Bundle
import android.service.controls.actions.ControlAction
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carvalho.todo.adapter.TarefaAdapter
import com.carvalho.todo.databinding.FragmentListBinding
import com.carvalho.todo.model.Tarefa
import com.carvalho.todo.repository.Repository

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Instância do binding
        binding = FragmentListBinding.inflate(layoutInflater, container, false)


        val repository = Repository()
        viewModel = MainViewModel(repository)
        viewModel.listaCategoria()
        viewModel.responseCategoria.observe(viewLifecycleOwner) { response ->
            Log.d("Requisicao", response.body().toString())
        }


        // Instância do adapter criado
        val tarefaAdapter = TarefaAdapter()

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
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }


        return binding.root
    }

}