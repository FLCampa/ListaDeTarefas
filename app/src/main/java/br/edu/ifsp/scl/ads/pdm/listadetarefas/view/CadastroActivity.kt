package br.edu.ifsp.scl.ads.pdm.listadetarefas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.edu.ifsp.scl.ads.pdm.listadetarefas.AutenticacaoFirebase
import br.edu.ifsp.scl.ads.pdm.listadetarefas.R
import br.edu.ifsp.scl.ads.pdm.listadetarefas.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var activityCadastroBinding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCadastroBinding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(activityCadastroBinding.root)
    }

    fun onClick(view: View) {

        if (view == activityCadastroBinding.cadastrarBt){
            val email = activityCadastroBinding.emailEt.text.toString()
            val senha = activityCadastroBinding.senhaEt.text.toString()
            val repetirSenha = activityCadastroBinding.repetirSenhaEt.text.toString()

            if (senha.equals(repetirSenha)) {
                AutenticacaoFirebase.firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { cadastro ->
                    if (cadastro.isSuccessful) {
                        Toast.makeText(this, "$email cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    else {
                        Toast.makeText(this, "Falha ao Cadastrar $email", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Senhas não são iguais", Toast.LENGTH_SHORT).show()
            }
        }
    }
}