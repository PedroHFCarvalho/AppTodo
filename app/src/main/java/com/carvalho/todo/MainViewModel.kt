package com.carvalho.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carvalho.todo.model.Categoria
import com.carvalho.todo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject



//@Inject injeta a dependencia na classe
//@HiltViewModel prepara a viewMOdel para receber uma dependencia
@HiltViewModel
// Passar para viewModel o Repository
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    // Atributo que recebe o resultado do método GET
    private var _responseCategoria = MutableLiveData<Response<List<Categoria>>>()

    // Recebe os dados da _responseCategoria disponibilizando o método para fora da classe com a possibilidade de alteração
    val responseCategoria: LiveData<Response<List<Categoria>>> = _responseCategoria

    // Método que fará a requisição dos dados da Api
    fun listaCategoria() {
        // viewModelScope.lauch executa o bloco do codigo de forma assíncrona
        viewModelScope.launch {
            try {
                val response = repository.listaCategoria()
                _responseCategoria.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }

        }

    }

}