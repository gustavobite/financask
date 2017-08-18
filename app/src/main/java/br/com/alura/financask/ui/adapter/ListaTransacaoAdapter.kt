package br.com.alura.financask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.util.formataParaBrasileiro

class ListaTransacaoAdapter(private val context: Context, private val transacoes: List<Transacao>) : BaseAdapter() {

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
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, viewGroup, false)

        val transacao = transacoes[position]

        val campoDescrico = view.findViewById<TextView>(R.id.transacao_categoria)
        adicionaCategoria(transacao, campoDescrico)

        val campoData = view.findViewById<TextView>(R.id.transacao_data)
        adicionaData(transacao, campoData)

        val campoValor = view.findViewById<TextView>(R.id.transacao_valor)
        adicionaValor(transacao, campoValor)

        val imagemDoIcone = view.findViewById<ImageView>(R.id.transacao_icone)
        adicionaIcone(transacao, imagemDoIcone)

        return view
    }

    private fun adicionaIcone(transacao: Transacao, imageView: ImageView) {
        if (ehTransacaoDeReceita(transacao)) {
            imageView.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        } else {
            imageView.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }
    }

    private fun adicionaData(transacao: Transacao, campoData: TextView) {
        campoData.setText(transacao.dataFormatada)
    }

    private fun adicionaValor(transacao: Transacao, campoValor: TextView) {
        if (ehTransacaoDeReceita(transacao)) {
            campoValor.setTextColor(ContextCompat.getColor(context, R.color.receita))
        } else {
            campoValor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
        }
        campoValor.setText(transacao.valor.formataParaBrasileiro())
    }

    private fun ehTransacaoDeReceita(transacao: Transacao): Boolean {
        return transacao.tipo.equals(Tipo.RECEITA)
    }

    private fun adicionaCategoria(transacao: Transacao, campoCategoria: TextView) {
        var categoria = transacao.categoria
        if (categoria.length > LIMITE_DE_CATEGORIA)
            categoria = categoria.substring(0, 14) + "..."
        campoCategoria.setText(categoria)
    }
}
