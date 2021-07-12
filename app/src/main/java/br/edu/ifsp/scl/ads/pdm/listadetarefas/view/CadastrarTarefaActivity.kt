package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityCadastrarTarefaBinding
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityTarefasBinding
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa

class CadastrarTarefaActivity : AppCompatActivity() {

    private lateinit var activityCadastrarTarefaBinding: ActivityCadastrarTarefaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCadastrarTarefaBinding = ActivityCadastrarTarefaBinding.inflate(layoutInflater)
        setContentView(activityCadastrarTarefaBinding.root)
    }

    fun onClick(view: View) {
        val tarefa: Tarefa
        with(activityCadastrarTarefaBinding) {
            tarefa = Tarefa(
                tituloEt.text.toString(),
                "",
                dataCriacaoEt.text.toString(),
                descricaoEt.text.toString(),
                dataCumprimentoEt.text.toString()
            )
        }

        if (view == activityCadastrarTarefaBinding.salvarBt) {
            val retornoIntent = Intent()
            retornoIntent.putExtra(Intent.EXTRA_USER, tarefa)
            setResult(RESULT_OK, retornoIntent)
            finish()
        }
    }
}