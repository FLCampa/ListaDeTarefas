package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.edu.ifsp.scl.ads.pdm.listadetarefas.AutenticacaoFirebase
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityRecuperarSenhaBinding

class RecuperarSenhaActivity : AppCompatActivity() {

    private lateinit var activityRecuperarSenhaBinding: ActivityRecuperarSenhaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRecuperarSenhaBinding = ActivityRecuperarSenhaBinding.inflate(layoutInflater)
        setContentView(activityRecuperarSenhaBinding.root)
    }

    fun onClick(view: View) {
        if (view == activityRecuperarSenhaBinding.enviarEmailBt) {
            val email = activityRecuperarSenhaBinding.emailRecuperarSenhaEt.text.toString()
            AutenticacaoFirebase.firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
                Toast.makeText(this, "E-mail de recuperação enviado para $email", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Falha no envio de e-mail de recuperação", Toast.LENGTH_SHORT).show()
            }
        }
    }
}