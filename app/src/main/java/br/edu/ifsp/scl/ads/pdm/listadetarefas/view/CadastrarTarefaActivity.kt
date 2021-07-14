package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.listadetarefas.AutenticacaoFirebase
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityCadastrarTarefaBinding
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


class CadastrarTarefaActivity : AppCompatActivity() {

    private lateinit var activityCadastrarTarefaBinding: ActivityCadastrarTarefaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCadastrarTarefaBinding = ActivityCadastrarTarefaBinding.inflate(layoutInflater)
        setContentView(activityCadastrarTarefaBinding.root)
        setTitle(R.string.cadastrar)
    }

    fun onClick(view: View) {
        val tarefa: Tarefa
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        with(activityCadastrarTarefaBinding) {
            tarefa = Tarefa(
                tituloEt.text.toString(),
                AutenticacaoFirebase.firebaseAuth.currentUser?.email.toString(),
                currentDate.toString(),
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

    override fun onStart() {
        super.onStart()
        if(AutenticacaoFirebase.firebaseAuth.currentUser == null){
            finish()
        }
    }

}