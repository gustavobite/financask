package br.com.alura.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.alura.financask.R
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.util.formataParaBrasileiro
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by alex on 16/08/17.
 */

class ResumoView(context: Context,
                 private val view: View) {

    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza(resumo: Resumo) {
        adicionaReceita(resumo)
        adicionaDespesa(resumo)
        adicionaTotal(resumo)
    }

    private fun adicionaTotal(resumo: Resumo) {
        val valorTotal = resumo.total
        with(view.resumo_card_total) {
            text = valorTotal.formataParaBrasileiro()
            setTextColor(corPor(valorTotal))
        }
    }

    private fun adicionaDespesa(resumo: Resumo) {
        val totalDespesa = resumo.despesa
        with(view.resumo_card_despesa) {
            text = totalDespesa.formataParaBrasileiro()
            setTextColor(corDespesa)
        }
    }

    private fun adicionaReceita(resumo: Resumo) {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            text = totalReceita.formataParaBrasileiro()
            setTextColor(corReceita)
        }
    }

    private fun corPor(total: BigDecimal): Int {
        if (total >= BigDecimal.ZERO) {
            return corReceita
        }
        return corDespesa
    }
}
