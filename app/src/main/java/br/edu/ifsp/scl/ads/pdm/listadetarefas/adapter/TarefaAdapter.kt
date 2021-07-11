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
        val titulo : TextView = viewTarefa.findViewById(R.id.titulo)
        val usuario : TextView = viewTarefa.findViewById(R.id.criacaoUsuario)
        val dataCriacao : TextView = viewTarefa.findViewById(R.id.dataCriacao)
        val descricao: TextView = viewTarefa.findViewById(R.id.descricao)
        val dataCumprimento: TextView = viewTarefa.findViewById(R.id.dataCumprimento)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val viewTarefa: View = LayoutInflater.from(parent.context).inflate(R.layout.view_tarefas, parent, false)
        return TarefaViewHolder(viewTarefa)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = tarefaList[position]

        holder.titulo.text = tarefa.titulo
        holder.usuario.text = tarefa.usuario
        holder.dataCriacao.text = tarefa.dataCriacao
        holder.descricao.text = tarefa.descricao
        holder.dataCumprimento.text = tarefa.dataCumprimento
        holder.itemView.setOnClickListener {
            onTarefaClickListener.onTarefaClick(position)
        }
    }

    override fun getItemCount(): Int = tarefaList.size
}