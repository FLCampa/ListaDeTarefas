package br.edu.ifsp.scl.ads.pdm.listadetarefas.model

interface TarefaDAO {
    fun createTarefa(tarefa: Tarefa)
    fun readTarefa(titulo: String): Tarefa
    fun readAllTarefas(): MutableList<Tarefa>
    fun updateTarefa(tarefa: Tarefa)
    fun deleteTarefa(titulo: String)
}