package br.edu.ifsp.scl.ads.pdm.listadetarefas.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa


class TarefaAdapter(
    private val tarefaList: MutableList<Tarefa>,
    private val onTarefaClickListener: OnClickListener,
    private val menuInflater: MenuInflater
): RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>(){

    inner class TarefaViewHolder(viewTarefa: View): RecyclerView.ViewHolder(viewTarefa), View.OnCreateContextMenuListener{
        val titulo : TextView = viewTarefa.findViewById(R.id.tituloTv)
        val usuario : TextView = viewTarefa.findViewById(R.id.usuarioTv)
        val dataCriacao : TextView = viewTarefa.findViewById(R.id.dataCriacaoTv)
        val descricao: TextView = viewTarefa.findViewById(R.id.descricaoTv)
        val dataCumprimento: TextView = viewTarefa.findViewById(R.id.dataCumprimentoTv)
        val usuarioCumpriu: TextView = viewTarefa.findViewById(R.id.usuarioCumpriuTv)
        init {
            viewTarefa.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu( menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo? ) {
            menuInflater.inflate(R.menu.context_menu_tarefa, menu)
        }
    }

    private var posicao: Int = 0

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
        holder.usuarioCumpriu.text = tarefa.usuarioCumpriu
        holder.itemView.setOnClickListener {
            onTarefaClickListener.onTarefaClick(position)
        }

        holder.itemView.setOnLongClickListener { v ->
            posicao = position
            false
        }
    }

    override fun getItemCount(): Int = tarefaList.size

    fun getPosicao(): Int {
        return posicao
    }
}