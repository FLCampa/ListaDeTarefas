package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.adapter.OnClickListener
import br.edu.ifsp.scl.ads.pdm.listadetarefas.adapter.TarefaAdapter
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityTarefasBinding
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa


class TarefasActivity : AppCompatActivity(), OnClickListener {
    private lateinit var activityTarefasBinding: ActivityTarefasBinding
    private lateinit var tarefasList: MutableList<Tarefa>
    private lateinit var tarefasAdapter: TarefaAdapter
    private lateinit var tarefasLayoutManager: LinearLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTarefasBinding = ActivityTarefasBinding.inflate(layoutInflater)
        setContentView(activityTarefasBinding.root)

        tarefasList = mutableListOf()

        for (i in  1..5){
            tarefasList.add(
                Tarefa(
                    "Titulo $i",
                    "User $i",
                    "Criação $i",
                    "Descrição $i",
                    "Cumprimento $i"
                )
            )
        }

        tarefasAdapter = TarefaAdapter(tarefasList, this)
        activityTarefasBinding.tarefasRv.adapter = tarefasAdapter
        tarefasLayoutManager = LinearLayoutManager(this)
        activityTarefasBinding.tarefasRv.layoutManager = tarefasLayoutManager


    }

    override fun onTarefaClick(posicao: Int) {
        val tarefa: Tarefa = tarefasList[posicao]
    }
}