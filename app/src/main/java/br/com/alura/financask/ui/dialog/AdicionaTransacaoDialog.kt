package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.View
import android.view.ViewGroup

import java.util.Calendar

import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.util.formataParaBrasileiro

/**
 * Created by alex on 16/08/17.
 */

class AdicionaTransacaoDialog(context: Context, view: View) : FormularioTransacaoDialog(context, view) {

    fun mostraFormulario(tipo: Tipo, delegate: (Transacao) -> Unit) {

        var titulo = devolveTitulo(tipo)

        adicionaValoresPadrao()
        mostraDialog(tipo, delegate, titulo, "Adicionar", "Cancelar")
    }

    private fun devolveTitulo(tipo: Tipo): String {
        return if (tipo == Tipo.RECEITA) {
            "Adicionar receita"
        } else {
            "Adicionar despesa"
        }
    }

    private fun adicionaValoresPadrao() {
        val calendar = Calendar.getInstance()
        val dataFormatada = calendar.formataParaBrasileiro()
        data.setText(dataFormatada)
    }

}
