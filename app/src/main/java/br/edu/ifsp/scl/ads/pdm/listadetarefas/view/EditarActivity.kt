package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityEditarBinding
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa

class EditarActivity : AppCompatActivity() {

    private lateinit var activityEditarBinding: ActivityEditarBinding
    private lateinit var tarefa: Tarefa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityEditarBinding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(activityEditarBinding.root)
        setTitle(R.string.editar)

        tarefa = (intent.getParcelableExtra(Intent.EXTRA_INDEX) as Tarefa?)!!

        activityEditarBinding.tituloEditarEt.setText(tarefa.titulo)
        activityEditarBinding.dataCriacaoEditarEt.setText(tarefa.dataCriacao)
        activityEditarBinding.descricaoEditarEt.setText(tarefa.descricao)
        activityEditarBinding.dataCumprimentoEditarEt.setText(tarefa.dataCumprimento)
        //ususario
    }

    fun onClick(view: View) {
        if(activityEditarBinding.descricaoEditarEt.text.toString() != ""){
            tarefa.descricao = activityEditarBinding.descricaoEditarEt.text.toString()
        }
        if(activityEditarBinding.dataCumprimentoEditarEt.text.toString() != ""){
            tarefa.dataCumprimento = activityEditarBinding.dataCumprimentoEditarEt.text.toString()
        }

        val retornoIntent = Intent()
        retornoIntent.putExtra(Intent.EXTRA_USER, tarefa)
        setResult(RESULT_OK,retornoIntent)
        finish()

    }
}