package br.com.alura.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.util.formataParaBrasileiro
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacaoAdapter(private val context: Context,
                            private val transacoes: List<Transacao>) : BaseAdapter() {

    private val LIMITE_DE_CATEGORIA = 14

    override fun getCount(): Int {
        return transacoes.size
    }

    override fun getItem(position: Int): Any {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        val viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.transacao_item, viewGroup, false)

        val transacao = transacoes[position]
        adicionaCategoria(transacao, viewCriada)
        adicionaData(transacao, viewCriada)
        adicionaValor(transacao, viewCriada)
        adicionaIcone(transacao, viewCriada)

        return viewCriada
    }

    private fun adicionaIcone(transacao: Transacao, viewCriada: View) {
        val icone = iconePor(transacao.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaData(transacao: Transacao, viewCriada: View) {
        viewCriada.transacao_data.text = transacao.dataFormatada
    }

    private fun adicionaValor(transacao: Transacao, viewCriada: View) {
        val cor = corPor(transacao.tipo)
        viewCriada.transacao_valor.setTextColor(cor)
        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)
    }

    private fun adicionaCategoria(transacao: Transacao, viewCriada: View) {
        val categoria = formataCategoria(transacao)
        viewCriada.transacao_categoria.text = categoria
    }

    private fun formataCategoria(transacao: Transacao): String {
        if (transacao.categoria.length > 14) {
            return "${transacao.categoria.substring(0, 14)}..."
        }
        return transacao.categoria
    }

}
