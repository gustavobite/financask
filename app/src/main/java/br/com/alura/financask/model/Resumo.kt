package br.com.alura.financask.model

import java.math.BigDecimal
import java.util.*

class Resumo {

    private val transacoes: MutableList<Transacao>

    constructor() {
        transacoes = ArrayList()
    }

    constructor(transacoes: MutableList<Transacao>) {
        this.transacoes = transacoes
    }

    fun getTransacoes(): List<Transacao> {
        return Collections.unmodifiableList(transacoes)
    }

    fun adiciona(vararg transacoes: Transacao) {
        for (transacao in transacoes) {
            this.transacoes.add(transacao)
        }
    }

    fun adiciona(transacoes: List<Transacao>) {
        for (transacao in transacoes) {
            this.transacoes.add(transacao)
        }
    }

    val receita: BigDecimal
        get() = somaTransacoesPorTipo(Tipo.RECEITA)

    val despesa: BigDecimal
        get() = somaTransacoesPorTipo(Tipo.DESPESA)

    private fun somaTransacoesPorTipo(tipo: Tipo): BigDecimal {
        var totalDeGasto = BigDecimal(0.0)
        for (transacao in transacoes) {
            if (transacao.tipo == tipo) {
                totalDeGasto = totalDeGasto.add(transacao.valor)
            }
        }
        return totalDeGasto.setScale(2, BigDecimal.ROUND_HALF_EVEN)
    }

    val total: BigDecimal
        get() = receita.subtract(despesa).setScale(2, BigDecimal.ROUND_HALF_EVEN)


}
