package br.edu.ifsp.scl.ads.pdm.listadetarefas.controller

import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.Tarefa
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.TarefaDAO
import br.edu.ifsp.scl.ads.pdm.listadetarefas.model.TarefaFirebase
import br.edu.ifsp.scl.ads.pdm.listadetarefas.view.TarefasActivity

class TarefaController(tarefasActivity: TarefasActivity) {
    val tarefaDAO : TarefaDAO

    init {
        tarefaDAO = TarefaFirebase(tarefasActivity)
    }

    fun insereTarefa(tarefa: Tarefa) = tarefaDAO.createTarefa(tarefa)
    fun buscaTarefa(titulo: String) = tarefaDAO.readTarefa(titulo)
    fun buscaTodasTarefas() = tarefaDAO.readAllTarefas()
    fun atualizaTarefa(tarefa: Tarefa) = tarefaDAO.updateTarefa(tarefa)
    fun removeTarefa(titulo: String) = tarefaDAO.deleteTarefa(titulo)
}