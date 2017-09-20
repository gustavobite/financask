package br.com.alura.financask.model

import br.com.alura.financask.model.Tipo.DESPESA
import br.com.alura.financask.model.Tipo.RECEITA
import java.math.BigDecimal


class Resumo(val transacoes: MutableList<Transacao> = mutableListOf()) {

    val receita: BigDecimal get() = somaTipo(RECEITA)

    val despesa: BigDecimal get() = somaTipo(DESPESA)

    val total: BigDecimal
        get() = receita.subtract(despesa).setScale(2, BigDecimal.ROUND_HALF_EVEN)

    private fun somaTipo(tipo: Tipo): BigDecimal {

//        var totalDeGasto = BigDecimal(0.0)
//
//
//        for (transacao in transacoes) {
//            if (transacao.tipo == tipo) {
//                totalDeGasto = totalDeGasto.add(transacao.valor)
//            }
//        }
        val totalDeGasto = BigDecimal(transacoes
                .filter {
                    it.tipo == tipo
                }
                .sumByDouble {
                    it.valor.toDouble()
                })
        return totalDeGasto.setScale(2, BigDecimal.ROUND_HALF_EVEN)
    }

}
