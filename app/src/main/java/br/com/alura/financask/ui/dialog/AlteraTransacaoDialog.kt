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
        val (titulo, categorias) = inicializaPor(tipo)

        adicionaValoresPadrao(transacao, categorias)

        mostraDialog(tipo, delegate, titulo, "Alterar", "Cancelar")
    }

    private fun inicializaPor(tipo: Tipo): Pair<String, Array<String>> {
        val categorias = devolveCategoria(tipo)
        val titulo = devolveTituo(tipo)
        return Pair(titulo, categorias)
    }

    private fun devolveCategoria(tipo: Tipo): Array<String> {
        return if (tipo == Tipo.RECEITA) {
            context.resources.getStringArray(R.array.categorias_de_receita)
        } else {
            context.resources.getStringArray(R.array.categorias_de_despesa)
        }
    }

    private fun devolveTituo(tipo: Tipo): String {
        return if (tipo == Tipo.RECEITA) {
            "Alterar receita"
        } else {
            "Alterar despesa"
        }
    }

    private fun adicionaValoresPadrao(transacao: Transacao, categorias: Array<String>) {
        val posicaoPadraoCategoria = devolvePosicaoDaCategoria(transacao, categorias)
        valor.setText(transacao.valorFormatado.toString())
        data.setText(transacao.dataFormatada)
        categoria.setSelection(posicaoPadraoCategoria)
    }

    protected fun devolvePosicaoDaCategoria(transacao: Transacao, categorias: Array<String>): Int {
        for (i in categorias.indices) {
            val categoria = categorias[i]
            if (categoria == transacao.categoria) {
                return i
            }
        }
        return 0
    }
}
