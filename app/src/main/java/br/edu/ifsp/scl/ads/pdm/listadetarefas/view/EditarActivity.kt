package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityCadastrarTarefaBinding
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa

class EditarActivity : AppCompatActivity() {

    private lateinit var activityEditarTarefaBinding: ActivityCadastrarTarefaBinding
    private lateinit var tarefa: Tarefa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditarTarefaBinding = ActivityCadastrarTarefaBinding.inflate(layoutInflater)
        setContentView(activityEditarTarefaBinding.root)
        setTitle(R.string.editar)

        //tarefa = (intent.getSerializableExtra(Intent.EXTRA_USER) as Tarefa?)!!


    }

    fun onClick(view: View) {}
}