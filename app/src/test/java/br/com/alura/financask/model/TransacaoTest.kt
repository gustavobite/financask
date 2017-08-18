package br.com.alura.financask.model

import org.junit.Test

import java.math.BigDecimal
import java.util.Calendar

import br.com.alura.financask.model.Tipo.DESPESA
import br.com.alura.financask.model.Tipo.RECEITA
import org.junit.Assert.assertEquals

class TransacaoTest {

    @Test
    fun criaTransacaoDeDebito() {
        val (_, tipoDevolvido) = Transacao(BigDecimal(100.0), DESPESA)
        assertEquals(DESPESA, tipoDevolvido)
    }

    @Test
    fun criaTransacaoDeReceita() {
        val (_, tipoDevolvido) = Transacao(BigDecimal(100.0), RECEITA)
        assertEquals(RECEITA, tipoDevolvido)
    }

    @Test
    fun criaTransacaoDeDebitoSemCategoria() {
        val (_, _, categoriaDevolvida) = Transacao(BigDecimal(100.0), DESPESA)
        assertEquals("Indefinida", categoriaDevolvida)
    }

    @Test
    fun criaTransacaoDeReceitaSemCategoria() {
        val (_, _, categoriaDevolvida) = Transacao(BigDecimal(100.0), RECEITA)
        assertEquals("Indefinida", categoriaDevolvida)
    }

    @Test
    fun criaTransacaoDeReceitaComCategoria() {
        val categoriaDeExemplo = "Almoço"
        val (_, _, categoriaDevolvida) = Transacao(BigDecimal(100.0), RECEITA, "Almoço")
        assertEquals(categoriaDeExemplo, categoriaDevolvida)
    }

    @Test
    fun criaTransacaoDeDebitoComCategoria() {
        val categoriaDeExemplo = "Almoço"
        val (_, _, categoriaDevolvida) = Transacao(BigDecimal(100.0), DESPESA, "Almoço")
        assertEquals(categoriaDeExemplo, categoriaDevolvida)
    }

    @Test
    fun criaTransacaoDeReceitaComData() {
        val data = Calendar.getInstance()
        data.set(2017, 10, 10)
        val (_, _, _, dataDevolvida) = Transacao(BigDecimal(100.0), RECEITA, data = data)
        assertEquals(data, dataDevolvida)
    }

    @Test
    fun criaTransacaoDeReceitaComDataECategoria() {
        val data = Calendar.getInstance()
        data.set(2017, 10, 10)
        val categoria = "Almoço"
        val (_, _, categoriaDevolvida, dataDevolvida) = Transacao(BigDecimal(100.0), RECEITA, data = data, categoria = categoria)
        assertEquals(data, dataDevolvida)
        assertEquals(categoria, categoriaDevolvida)
    }


}