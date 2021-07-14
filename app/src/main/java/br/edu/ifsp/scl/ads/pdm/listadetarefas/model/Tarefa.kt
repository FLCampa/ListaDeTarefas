package br.edu.ifsp.scl.ads.pdm.listadetarefas.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Tarefa (
        val titulo: String = "",
        val usuario: String = "",
        val dataCriacao: String = "",
        var descricao: String = "",
        var dataCumprimento: String = "",
        var usuarioCumpriu: String = "",
): Parcelable