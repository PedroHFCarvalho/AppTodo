package com.carvalho.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carvalho.todo.MainViewModel
import com.carvalho.todo.R
import com.carvalho.todo.model.Tarefa

class TarefaAdapter(
    private val taskItemClickListener: TaskItemClickListener,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listTarefa = emptyList<Tarefa>()

    // Cria o modelo em tela
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_tarefas, parent, false)
        return TarefaViewHolder(layout)
    }

    // Exibe na tela o modelo criado com os dados recuperados
    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {

        val tarefa = listTarefa[position]

        holder.txtTitulo.text = tarefa.nome
        holder.txtDescricao.text = tarefa.descricao
        holder.txtResponsavel.text = tarefa.responsavel
        holder.txtData.text = tarefa.data
        holder.swtAndamento.isChecked = tarefa.status
        holder.txtCategoria.text = tarefa.categoria.descricao

        // Evendo que identifica um click emcima do card
        holder.itemView.setOnClickListener {
            taskItemClickListener.onTaskClicked(tarefa)
        }

        // Evento de Click no Botao Deletar
        holder.btnDeletar.setOnClickListener {
            viewModel.deleteTarefa(
                tarefa.id
            )

        }

        // Evento que identifica a mudança de estado do Switch
        holder.swtAndamento.setOnCheckedChangeListener { compoudButton, estado ->
            tarefa.status = estado
            viewModel.updateTarefa(tarefa)

        }


    }

    // Conta quantos itens existem na lista
    override fun getItemCount(): Int {
        return listTarefa.size
    }

    // Mapeia na tela item a serem alterados
    class TarefaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtTitulo = view.findViewById<TextView>(R.id.txtTitulo)
        val txtDescricao = view.findViewById<TextView>(R.id.txtDescricao)
        val txtResponsavel = view.findViewById<TextView>(R.id.txtResponsavel)
        val txtData = view.findViewById<TextView>(R.id.txtData)
        val swtAndamento = view.findViewById<Switch>(R.id.swtAndamento)
        val txtCategoria = view.findViewById<TextView>(R.id.txtCategoria)
        val btnDeletar = view.findViewById<ImageButton>(R.id.btnDelete)

    }

    // Traz a lista de item completa
    fun setLista(list: List<Tarefa>) {
        listTarefa = list
        notifyDataSetChanged()
    }


}