package br.com.alura.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.widget.TextView
import br.com.alura.financask.R
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.util.formataParaBrasileiro
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by alex on 16/08/17.
 */

class ResumoView(context: Context, private val viewRoot: ViewGroup) {

    val corReceita = ContextCompat.getColor(context, R.color.receita)
    val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza(resumo: Resumo) {
        adicionaReceita(resumo, viewRoot.resumo_card_receita)
        adicionaDespesa(resumo, viewRoot.resumo_card_despesa)
        adicionaTotal(resumo, viewRoot.resumo_card_total)
    }

    private fun adicionaTotal(resumo: Resumo, total: TextView) {
        val valorTotal = resumo.total
        with(total) {
            text = valorTotal.formataParaBrasileiro()
            setTextColor(devolveCorPor(valorTotal))
        }
    }

    private fun adicionaDespesa(resumo: Resumo, despesa: TextView) {
        with(despesa) {
            text = resumo.despesa.formataParaBrasileiro()
            setTextColor(corDespesa)
        }
    }

    private fun adicionaReceita(resumo: Resumo, receita: TextView) {
        with(receita) {
            text = resumo.receita.formataParaBrasileiro()
            setTextColor(corReceita)
        }
    }

    private fun devolveCorPor(total: BigDecimal): Int {
        return if (total >= BigDecimal.ZERO) {
            corReceita
        } else {
            corDespesa
        }
    }
}
