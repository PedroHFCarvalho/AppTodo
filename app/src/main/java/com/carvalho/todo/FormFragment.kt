package com.carvalho.todo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.R
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.carvalho.todo.databinding.FragmentFormBinding
import com.carvalho.todo.fragment.DatePickerFragment
import com.carvalho.todo.model.Categoria
import com.carvalho.todo.model.Tarefa
import java.time.LocalDate


// DatePickerFragment.TimePickerListener traz a classe criada com as configurações da data
class FormFragment : Fragment(), DatePickerFragment.TimePickerListener {

    private lateinit var binding: FragmentFormBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var categoriaSelc = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFormBinding.inflate(layoutInflater, container, false)
        viewModel.listaTarefa()

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

        binding.btnSalvar.setOnClickListener {
            inserirNoBanco()

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

            binding.spinnerCategoria.onItemSelectedListener =
                    // vai pegar o Id da categoria e guardala em uma variavel
                object : AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val categoriaSelcFun = binding.spinnerCategoria.selectedItem as Categoria
                        categoriaSelc = categoriaSelcFun.id
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }


        }
    }

    override fun onTimeSelected(date: LocalDate) { // Método que coloca a data selecionada no textEdit
        viewModel.dataSelecionada.value = date
    }

    // Valida todos os campos do form
    fun validaCampos(nome: String, desc: String, responsavel: String, data: String): Boolean {
        return !(nome.isBlank() || nome.length < 3 || nome.length > 20) ||
                (desc.isBlank() || desc.length < 5 || desc.length > 200) ||
                (responsavel.isBlank() || responsavel.length < 3 || responsavel.length > 20) ||
                (data.isBlank())
    }

    // Instancia o Objeto e lanca ele para o banco de dados
    fun inserirNoBanco() {
        val nome = binding.editNome.text.toString()
        val desc = binding.editDescricao.text.toString()
        val responsavel = binding.editResponsavel.text.toString()
        val data = binding.editData.text.toString()
        val status = binding.switchAndamento.isChecked
        val categoria = Categoria(categoriaSelc, null, null)

        if (validaCampos(nome, desc, responsavel, data)) {
            val terefa = Tarefa(0, nome, desc, responsavel, data, status, categoria)
            viewModel.addTarefa(terefa)
            Toast.makeText(context, "Tarefa Salva", Toast.LENGTH_LONG).show()
            findNavController().navigate(com.carvalho.todo.R.id.action_formFragment_to_listFragment)

        } else {
            Toast.makeText(context, "Preencha os campos corremtamente", Toast.LENGTH_LONG).show()
        }
    }


}