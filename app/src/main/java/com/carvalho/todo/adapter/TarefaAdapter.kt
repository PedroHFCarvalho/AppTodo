package com.carvalho.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carvalho.todo.R
import com.carvalho.todo.model.Tarefa

class TarefaAdapter : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listTarefa = emptyList<Tarefa>()

    // Cria o modelo em tela
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_tarefas, parent, false)
        return TarefaViewHolder(layout)
    }

    // Exibe na tela o modelo criado com os dados recuberados
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listTarefa[position]

        holder.txtTitulo.text = tarefa.titulo
        holder.txtDescricao.text = tarefa.descricao
        holder.txtResponsavel.text = tarefa.responsavel
        holder.txtData.text = tarefa.data
        holder.swtAndamento.isChecked = tarefa.andamento
        holder.txtCategoria.text = tarefa.categoria

    }
    // conta quantos item existem na lista
    override fun getItemCount(): Int {
        return listTarefa.size
    }

    //mapeia na tela item a serem alterados
    inner class TarefaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtTitulo = view.findViewById<TextView>(R.id.txtTitulo)
        val txtDescricao = view.findViewById<TextView>(R.id.txtDescricao)
        val txtResponsavel = view.findViewById<TextView>(R.id.txtResponsavel)
        val txtData = view.findViewById<TextView>(R.id.txtData)
        val swtAndamento = view.findViewById<Switch>(R.id.swtAndamento)
        val txtCategoria = view.findViewById<TextView>(R.id.txtCategoria)

    }
    // traz a lista de item complera
    fun setLista(list: List<Tarefa>) {
        listTarefa = list
        notifyDataSetChanged()
    }


}