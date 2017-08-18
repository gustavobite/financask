package br.com.alura.financask.dao

import br.com.alura.financask.model.Transacao

class TransacaoDAO {

    val transacoes: MutableList<Transacao>
        get() = TRANSACOES

    fun adiciona(vararg transacoes: Transacao) {
        for (transacao in transacoes) {
            TRANSACOES.add(transacao)
        }
    }

    fun adiciona(transacoes: List<Transacao>) {
        for (transacao in transacoes) {
            TRANSACOES.add(transacao)
        }
    }

    fun remove(position: Int) {
        TRANSACOES.removeAt(position)
    }

    fun altera(transacao: Transacao, position: Int) {
        TRANSACOES[position] = transacao
    }

    companion object {
        private val TRANSACOES = mutableListOf<Transacao>()
    }
}
