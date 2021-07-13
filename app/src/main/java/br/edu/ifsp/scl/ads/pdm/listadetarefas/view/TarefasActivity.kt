package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.pdm.listadetarefas.AutenticacaoFirebase
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.adapter.OnClickListener
import br.edu.ifsp.scl.ads.pdm.listadetarefas.adapter.TarefaAdapter
import br.edu.ifsp.scl.ads.pdm.listadetarefas.controller.TarefaController
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityTarefasBinding
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa


class TarefasActivity : AppCompatActivity(), OnClickListener {
    private lateinit var activityTarefasBinding: ActivityTarefasBinding
    private lateinit var tarefasList: MutableList<Tarefa>
    private lateinit var tarefasAdapter: TarefaAdapter
    private lateinit var tarefasLayoutManager: LinearLayoutManager
    private lateinit var tarefa: Tarefa
    private lateinit var tarefaController: TarefaController

    private lateinit var novaTarefaLauncher: ActivityResultLauncher<Intent>
    private lateinit var editarTarefaLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTarefasBinding = ActivityTarefasBinding.inflate(layoutInflater)
        setContentView(activityTarefasBinding.root)
        setTitle(R.string.lista_de_tarefas)
        tarefasList = mutableListOf()
        tarefaController = TarefaController(this)
        tarefasList = tarefaController.buscaTodasTarefas()

//        for (i in  1..10){
//            tarefasList.add(
//                Tarefa(
//                    "Titulo $i",
//                    "User $i",
//                    "Criação $i",
//                    "Descrição $i",
//                    "Cumprimento $i"
//                )
//            )
//        }

        tarefasAdapter = TarefaAdapter(tarefasList, this, menuInflater)
        activityTarefasBinding.tarefasRv.adapter = tarefasAdapter
        tarefasLayoutManager = LinearLayoutManager(this)
        activityTarefasBinding.tarefasRv.layoutManager = tarefasLayoutManager


        novaTarefaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val tarefa: Tarefa? = activityResult.data?.getParcelableExtra<Tarefa>(Intent.EXTRA_USER)
                if (tarefa != null) {
                    tarefasList.add(tarefa)
                    tarefasAdapter.notifyDataSetChanged()

                    tarefaController.insereTarefa(tarefa)
                }
            }
        }

        editarTarefaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val tarefa: Tarefa? = activityResult.data?.getParcelableExtra<Tarefa>(Intent.EXTRA_USER)
                if (tarefa != null) {

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
            val novaTarefaIntent = Intent(this, CadastrarTarefaActivity::class.java)
            novaTarefaLauncher.launch(novaTarefaIntent)
            true
        }
        R.id.sairMi -> {
            AutenticacaoFirebase.firebaseAuth.signOut()
            AutenticacaoFirebase.googleSignInClient?.signOut()
            true
        }
        else -> {
            false
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menuInflater.inflate(R.menu.context_menu_tarefa, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        tarefa = tarefasList.get(tarefasAdapter.getPosicao())

        val posicao: Int = tarefasAdapter.getPosicao()

        when(item.itemId){
            R.id.editarTarefaMi -> {
                val editarTarefaIntent = Intent(this, EditarActivity::class.java)
                editarTarefaIntent.putExtra(Intent.EXTRA_USER, tarefa)
                editarTarefaLauncher.launch(editarTarefaIntent)
                return true
            }
            R.id.removerTarefaMi -> {
                Toast.makeText(this, tarefa.titulo + " foi removido!", Toast.LENGTH_SHORT).show()
                tarefasList.remove(tarefa)
                tarefasAdapter.notifyDataSetChanged()
                tarefaController.removeTarefa(tarefa.titulo)
                return true
            }
        }
        return false
    }

    override fun onStart() {
        super.onStart()
        if(AutenticacaoFirebase.firebaseAuth.currentUser == null){
            finish()
        }
    }
}