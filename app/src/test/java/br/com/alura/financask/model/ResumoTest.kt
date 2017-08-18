package br.com.alura.financask.model

import br.com.alura.financask.model.Tipo.DESPESA
import br.com.alura.financask.model.Tipo.RECEITA
import org.junit.Test

import java.math.BigDecimal
import java.util.ArrayList
import java.util.Arrays

import org.junit.Assert.assertEquals

class ResumoTest {

    @Test
    fun adicionaReceita() {
        val resumoDeTransacoes = Resumo()
        val receita = Transacao(BigDecimal(100.0), RECEITA)
        resumoDeTransacoes.adiciona(receita)
        val transacaoDevolvida = resumoDeTransacoes.getTransacoes()[0]

        val valor = transacaoDevolvida.valor
        assertEquals(BigDecimal(100.0), valor)

        val tipoDevolvido = transacaoDevolvida.tipo
        assertEquals(RECEITA, tipoDevolvido)

        assertEquals(receita, transacaoDevolvida)
    }

    @Test
    fun adicionaDebito() {
        val resumoDeTransacoes = Resumo()
        val despesa = Transacao(BigDecimal(100.0), DESPESA)
        resumoDeTransacoes.adiciona(despesa)
        val transacaoDevolvida = resumoDeTransacoes.getTransacoes()[0]

        val valor = transacaoDevolvida.valor
        assertEquals(BigDecimal(100.0), valor)

        val tipoDevolvido = transacaoDevolvida.tipo
        assertEquals(DESPESA, tipoDevolvido)

        assertEquals(despesa, transacaoDevolvida)
    }

    @Test
    fun adicionaTransacoes() {
        val resumoDeTransacoes = Resumo()
        resumoDeTransacoes.adiciona(transacoesDeExemplo)
        val transacoesDevolvidas = resumoDeTransacoes.getTransacoes()
        val totalTransacoes = transacoesDevolvidas.size
        assertEquals(10, totalTransacoes.toLong())
    }

    @Test
    fun retornaTotalDoResumo() {
        val ResumoDeTransacoes = Resumo()
        ResumoDeTransacoes.adiciona(transacoesDeExemplo)
        val valorArredondadoEsperado = BigDecimal(234.57).setScale(2, BigDecimal.ROUND_HALF_EVEN)
        val receita = ResumoDeTransacoes.total
        assertEquals(valorArredondadoEsperado, receita)
    }

    @Test
    fun retornaTotalDeReceita() {
        val resumoDeTransacoes = Resumo()
        resumoDeTransacoes.adiciona(transacoesDeExemplo)
        val valorArredondadoEsperado = BigDecimal(472.26).setScale(2, BigDecimal.ROUND_HALF_EVEN)
        val receita = resumoDeTransacoes.receita
        assertEquals(valorArredondadoEsperado, receita)
    }

    @Test
    fun retornaTotalDeGasto() {
        val resumoDeTransacoes = Resumo()
        resumoDeTransacoes.adiciona(transacoesDeExemplo)
        val despesa = resumoDeTransacoes.despesa
        val valorArredondadoEsperado = BigDecimal(237.69).setScale(2, BigDecimal.ROUND_HALF_EVEN)
        assertEquals(valorArredondadoEsperado, despesa)
    }

    private val transacoesDeExemplo: List<Transacao>
        get() = ArrayList(Arrays.asList(
                Transacao(BigDecimal(120.0), DESPESA),
                Transacao(BigDecimal(20.0), RECEITA),
                Transacao(BigDecimal(30.21), DESPESA),
                Transacao(BigDecimal(44.54), RECEITA),
                Transacao(BigDecimal(55.46), DESPESA),
                Transacao(BigDecimal(78.32), RECEITA),
                Transacao(BigDecimal(12.02), DESPESA),
                Transacao(BigDecimal(129.40), RECEITA),
                Transacao(BigDecimal(20.0), DESPESA),
                Transacao(BigDecimal(200.0), RECEITA)
        ))

}