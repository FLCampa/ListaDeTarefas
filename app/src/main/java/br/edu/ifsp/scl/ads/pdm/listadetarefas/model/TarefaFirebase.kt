package br.edu.ifsp.scl.ads.pdm.listadetarefas.model


import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.TarefaFirebase.Constantes.LISTA_TAREFAS_DATABASE
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class TarefaFirebase: TarefaDAO {

    object Constantes {
        val LISTA_TAREFAS_DATABASE = "listaDeTarefas"
    }

    private val tarafasListRtDb = Firebase.database.getReference(LISTA_TAREFAS_DATABASE)
    private val tarefasList: MutableList<Tarefa> = mutableListOf()

    init {
        tarafasListRtDb.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val novaTarefa: Tarefa = snapshot.getValue<Tarefa>()?:Tarefa()
                if (tarefasList.indexOfFirst { it.titulo.equals(novaTarefa.titulo) } == -1) {
                    tarefasList.add(novaTarefa)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val tarafaEditada: Tarefa = snapshot.getValue<Tarefa>()?:Tarefa()
                val idx = tarefasList.indexOfFirst { it.titulo.equals(tarafaEditada.titulo) }
                tarefasList[idx] = tarafaEditada
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val tarefaRemovida: Tarefa = snapshot.getValue<Tarefa>()?:Tarefa()
                tarefasList.remove(tarefaRemovida)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onCancelled(error: DatabaseError) {}
        })

        tarafasListRtDb.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var tarefa :Tarefa =  snapshot.getValue<Tarefa>()?:Tarefa()
                tarefasList.add(tarefa)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun createTarefa(tarefa: Tarefa) = criaOuAtulizaTarefa(tarefa)

    override fun readTarefa(titulo: String): Tarefa = tarefasList[ tarefasList.indexOfFirst { it.titulo.equals(titulo) }]

    override fun readAllTarefas(): MutableList<Tarefa> = tarefasList

    override fun updateTarefa(tarefa: Tarefa) = criaOuAtulizaTarefa(tarefa)

    override fun deleteTarefa(titulo: String) {
        tarafasListRtDb.child(titulo).removeValue()
    }

    private fun criaOuAtulizaTarefa(tarefa: Tarefa) {
        tarafasListRtDb.child(tarefa.titulo).setValue(tarefa)
    }
}