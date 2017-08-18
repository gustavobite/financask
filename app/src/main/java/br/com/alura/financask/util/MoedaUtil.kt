package br.com.alura.financask.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

/**
 * Created by alex on 18/08/17.
 */

object MoedaUtil {

    fun formataParaBrasileiro(valor: BigDecimal): String {
        val moeda = DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
        return moeda.format(valor).replace("R$", "R$ ").replace("-R$ ", "R$ -")
    }

    fun validaMoeda(valorEmTexto: String): BigDecimal {
        return if (!valorEmTexto.isEmpty()) {
            BigDecimal(valorEmTexto)
        } else BigDecimal.ZERO
    }
}
