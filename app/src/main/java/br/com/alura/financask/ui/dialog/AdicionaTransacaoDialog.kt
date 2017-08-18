package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup

import java.util.Calendar

import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.util.DataUtil

/**
 * Created by alex on 16/08/17.
 */

class AdicionaTransacaoDialog(context: Context, viewRoot: ViewGroup) : FormularioTransacaoDialog(context, viewRoot) {

    fun mostraFormulario(tipo: Tipo, delegate: (Transacao) -> Unit) {
        var titulo = "Adicionar despesa"
        if (tipo.equals(Tipo.RECEITA)) {
            titulo = "Adicionar receita"
        }
        adicionaValoresPadrao()
        mostraDialog(tipo, delegate, titulo, "Adicionar", "Cancelar")
    }

    private fun adicionaValoresPadrao() {
        val calendar = Calendar.getInstance()
        val dataFormatada = DataUtil.formataParaBrasileiro(calendar)
        data.setText(dataFormatada)
    }

}
