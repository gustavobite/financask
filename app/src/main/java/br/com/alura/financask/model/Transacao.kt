package br.com.alura.financask.model

import br.com.alura.financask.util.DataUtil
import java.math.BigDecimal
import java.util.*

data class Transacao(val valor: BigDecimal,
                     val tipo: Tipo,
                     var categoria: String = INDEFINIDA,
                     var data: Calendar = Calendar.getInstance()) {

    companion object {
        val INDEFINIDA = "Indefinida"
    }

    fun valorFormatado(): BigDecimal = valor.setScale(2, BigDecimal.ROUND_HALF_EVEN)

    fun dataFormatada(): String = DataUtil.formataParaBrasileiro(data)
}
