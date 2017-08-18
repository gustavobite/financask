package br.com.alura.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup

import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao

/**
 * Created by alex on 16/08/17.
 */

class AlteraTransacaoDialog(context: Context, viewRoot: ViewGroup) : FormularioTransacaoDialog(context, viewRoot) {

    fun mostraFormulario(transacao: Transacao, delegate: (Transacao) -> Unit) {
        val tipo = transacao.tipo

        var titulo = "Alterar despesa"
        var categorias = context.resources.getStringArray(R.array.categorias_de_despesa)
        if (tipo.equals(Tipo.RECEITA)) {
            titulo = "Alterar receita"
            categorias = context.resources.getStringArray(R.array.categorias_de_receita)
        }

        adicionaValoresPadrao(transacao, categorias)

        mostraDialog(tipo, delegate, titulo, "Alterar", "Cancelar")
    }

    private fun adicionaValoresPadrao(transacao: Transacao, categorias: Array<String>) {
        val posicaoPadraoCategoria = devolvePosicaoDaCategoria(transacao, categorias)
        valor.setText(transacao.valorFormatado.toString())
        data.setText(transacao.dataFormatada)
        categoria.setSelection(posicaoPadraoCategoria)
    }

    protected fun devolvePosicaoDaCategoria(transacao: Transacao, categorias: Array<String>): Int {
        val primeiroItem = 0
        for (i in categorias.indices) {
            val categoria = categorias[i]
            if (categoria == transacao.categoria) {
                return i
            }
        }
        return primeiroItem
    }
}
