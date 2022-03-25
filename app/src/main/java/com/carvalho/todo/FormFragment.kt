package com.carvalho.todo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.R
import androidx.fragment.app.activityViewModels
import com.carvalho.todo.databinding.FragmentFormBinding
import com.carvalho.todo.fragment.DatePickerFragment
import com.carvalho.todo.model.Categoria
import java.time.LocalDate


// DatePickerFragment.TimePickerListener traz a classe criada com as configurações da data
class FormFragment : Fragment(), DatePickerFragment.TimePickerListener {

    private lateinit var binding: FragmentFormBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        viewModel.responseCategoria.observe(viewLifecycleOwner) { response ->
            Log.d("Requisicao", response.body().toString())
            setSpinnerCategoria(response.body()) // Chama o Método para povoar o spinner com uma lista de categoria
        }

        viewModel.dataSelecionada.observe(viewLifecycleOwner) { dateNow ->
            binding.editData.setText(dateNow.toString()) // Passa para o editData a data atual

        }

        binding.editData.setOnClickListener { // Abre o calendário com a data atual
            DatePickerFragment(this).show(parentFragmentManager, "DatePicker")
        }

        return binding.root
    }

    private fun setSpinnerCategoria(categorias: List<Categoria>?) { // Método para povoar o spinner com base em uma array de Categoria
        if (categorias != null) { // Verifica se a lista é vazia
            binding.spinnerCategoria.adapter =
                ArrayAdapter( // Povoa o adapter do spinner com a lista e ordena ela em um dropdown_item
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    categorias
                )
        }
    }

    override fun onTimeSelected(date: LocalDate) { // Método que coloca a data selecionada no textEdit
        viewModel.dataSelecionada.value = date
    }

}