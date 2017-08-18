package br.com.alura.financask.dao

import br.com.alura.financask.model.Transacao

class TransacaoDAO {

    val transacoes: List<Transacao> get() = Companion.transacoes

    fun adiciona(vararg transacoes: Transacao) {
        for (transacao in transacoes) {
            Companion.transacoes.add(transacao)
        }
    }

    fun remove(position: Int) {
        Companion.transacoes.removeAt(position)
    }

    fun altera(transacao: Transacao, position: Int) {
        Companion.transacoes[position] = transacao
    }

    companion object {
        private val transacoes = mutableListOf<Transacao>()
    }
}
