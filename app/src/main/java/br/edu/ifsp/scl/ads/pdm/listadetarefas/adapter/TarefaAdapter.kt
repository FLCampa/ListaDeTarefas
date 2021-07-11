package br.edu.ifsp.scl.ads.pdm.listadetarefas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa


class TarefaAdapter(
    private val tarefaList: MutableList<Tarefa>,
    private val onTarefaClickListener: OnClickListener
): RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    inner class TarefaViewHolder(viewTarefa: View): RecyclerView.ViewHolder(viewTarefa){
        val criacaoUsuario : TextView = viewTarefa.findViewById(R.id.criacaoUsuario)
        val dataCriacao : TextView = viewTarefa.findViewById(R.id.dataCriacao)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val viewTarefa: View = LayoutInflater.from(parent.context).inflate(R.layout.view_tarefa, parent, false)
        return TarefaViewHolder(viewTarefa)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = tarefaList[position]

        holder.dataCriacao.text = tarefa.dataCriacao
        holder.criacaoUsuario.text = tarefa.criacaoUsuario
        holder.itemView.setOnClickListener {
            onTarefaClickListener.onTarefaClick(position)
        }
    }

    override fun getItemCount(): Int = tarefaList.size
}