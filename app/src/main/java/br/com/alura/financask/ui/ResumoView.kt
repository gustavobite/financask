package br.com.alura.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import android.widget.TextView
import br.com.alura.financask.R
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.util.MoedaUtil
import java.math.BigDecimal

/**
 * Created by alex on 16/08/17.
 */

class ResumoView(private val context: Context, private val viewRoot: ViewGroup) {

    fun atualiza(resumo: Resumo) {
        val campoTotal = viewRoot.findViewById<TextView>(R.id.resumo_card_total)
        val campoReceita = viewRoot.findViewById<TextView>(R.id.resumo_card_receita)
        val campoDespesa = viewRoot.findViewById<TextView>(R.id.resumo_card_despesa)

        val corReceita = R.color.receita
        val corDespesa = R.color.despesa
        campoReceita.setTextColor(ContextCompat.getColor(context, corReceita))
        campoDespesa.setTextColor(ContextCompat.getColor(context, corDespesa))

        campoReceita.setText(MoedaUtil.formataParaBrasileiro(resumo.despesa))
        campoDespesa.setText(MoedaUtil.formataParaBrasileiro(resumo.despesa))

        val total = resumo.total
        if (total.compareTo(BigDecimal.ZERO) >= 0)
            campoTotal.setTextColor(ContextCompat.getColor(context, corReceita))
        else
            campoTotal.setTextColor(ContextCompat.getColor(context, corDespesa))
        campoTotal.setText(MoedaUtil.formataParaBrasileiro(total))
    }
}
