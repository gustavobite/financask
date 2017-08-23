package br.com.alura.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.widget.TextView
import br.com.alura.financask.R
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.util.formataParaBrasileiro
import java.math.BigDecimal

/**
 * Created by alex on 16/08/17.
 */

class ResumoView(private val context: Context, private val viewRoot: ViewGroup) {

    val corReceita = ContextCompat.getColor(context, R.color.receita)
    val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza(resumo: Resumo) {
        val campoReceita = viewRoot.findViewById<TextView>(R.id.resumo_card_receita)
        campoReceita.text = resumo.receita.formataParaBrasileiro()
        campoReceita.setTextColor(corReceita)

        val campoDespesa = viewRoot.findViewById<TextView>(R.id.resumo_card_despesa)
        campoDespesa.text = resumo.despesa.formataParaBrasileiro()
        campoDespesa.setTextColor(corDespesa)

        val campoTotal = viewRoot.findViewById<TextView>(R.id.resumo_card_total)
        val total = resumo.total
        campoTotal.text = total.formataParaBrasileiro()
        campoTotal.setTextColor(devolveCorPor(total))
    }

    private fun devolveCorPor(total: BigDecimal): Int {
        return if (total.compareTo(BigDecimal.ZERO) >= 0) {
            corReceita
        } else {
            corDespesa
        }
    }
}
