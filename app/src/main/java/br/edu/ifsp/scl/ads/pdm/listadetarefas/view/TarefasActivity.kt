package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

    private lateinit var novaTarefaLauncher: ActivityResultLauncher<Intent>

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


        novaTarefaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val tarefa: Tarefa? = activityResult.data?.getParcelableExtra<Tarefa>(Intent.EXTRA_USER)
                if (tarefa != null) {
                    tarefasList.add(tarefa)
                    tarefasAdapter.notifyDataSetChanged()


                }
            }
        }

    }

    override fun onTarefaClick(posicao: Int) {
        val tarefa: Tarefa = tarefasList[posicao]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.novaTarefaMi -> {
            val novaTarefaIntent = Intent(this, CadastroActivity::class.java)
            novaTarefaLauncher.launch(novaTarefaIntent)
            true
        }
        else -> {
            false
        }
    }

}