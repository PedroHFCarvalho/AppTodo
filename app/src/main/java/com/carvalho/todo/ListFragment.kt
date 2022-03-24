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
import com.carvalho.todo.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Instância do binding
        binding = FragmentListBinding.inflate(layoutInflater, container, false)


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