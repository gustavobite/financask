package br.com.alura.financask.delegate

import br.com.alura.financask.model.Transacao

/**
 * Created by alex on 16/08/17.
 */

interface TransacaoDelegate {
    fun executa(transacao: Transacao)
}
