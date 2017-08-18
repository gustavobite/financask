package br.com.alura.financask.model

import br.com.alura.financask.util.formataParaBrasileiro
import java.math.BigDecimal
import java.util.*

data class Transacao(val valor: BigDecimal,
                     val tipo: Tipo,
                     var categoria: String = INDEFINIDA,
                     var data: Calendar = Calendar.getInstance()) {

    companion object {
        val INDEFINIDA = "Indefinida"
    }

    val valorFormatado: BigDecimal get() = valor.setScale(2, BigDecimal.ROUND_HALF_EVEN)

    val dataFormatada: String get() = data.formataParaBrasileiro()

}


