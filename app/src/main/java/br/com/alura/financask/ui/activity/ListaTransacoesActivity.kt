package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.alura.financask.R
import br.com.alura.financask.dao.TransacaoDAO
import br.com.alura.financask.model.Resumo
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Tipo.DESPESA
import br.com.alura.financask.model.Tipo.RECEITA
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.ui.ResumoView
import br.com.alura.financask.ui.adapter.ListaTransacaoAdapter
import br.com.alura.financask.ui.dialog.AdicionaTransacaoDialog
import br.com.alura.financask.ui.dialog.AlteraTransacaoDialog
import br.com.alura.financask.ui.dialog.FiltroDialog
import com.github.clans.fab.FloatingActionButton
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private lateinit var transacoes: MutableList<Transacao>
    private lateinit var viewRoot: ViewGroup
    private lateinit var resumoView: ResumoView
    private lateinit var adapter: ListaTransacaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        viewRoot = findViewById<View>(android.R.id.content) as ViewGroup
        transacoes = TransacaoDAO().transacoes
        resumoView = ResumoView(this, viewRoot)
        configuraListaTransacoes()
        configuraFabMenu()
    }

    private fun configuraFabMenu() {
        lista_transacoes_adiciona_menu.setClosedOnTouchOutside(true)
        configuraFab(RECEITA, lista_transacoes_adiciona_receita)
        configuraFab(DESPESA, lista_transacoes_adiciona_despesa)
    }

    private fun configuraFab(tipo: Tipo, fab: FloatingActionButton) {
        fab.setOnClickListener({
            AdicionaTransacaoDialog(this@ListaTransacoesActivity, viewRoot)
                    .mostraFormulario(tipo, {
                        adicionaTransacao(it)
                    })
            lista_transacoes_adiciona_menu.close(true)
        })
    }

    private fun configuraListaTransacoes() {
        configuraAdapter()
        lista_transacoes_listview.setOnCreateContextMenuListener { contextMenu, _, _ ->
            contextMenu.add(Menu.NONE, 1, Menu.NONE, "Remover")
        }

        lista_transacoes_listview.onItemClickListener = AdapterView.OnItemClickListener { _, _, posicao, _ ->
            val transacaoDevolvida = transacoes[posicao]

            AlteraTransacaoDialog(this@ListaTransacoesActivity, viewRoot)
                    .mostraFormulario(transacaoDevolvida, {
                        alteraTransacao(it, posicao)
                    })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.menu_principal_filtro) {
            FiltroDialog(this, viewRoot).mostraFormulario({
                val transacoesSalvas = TransacaoDAO().transacoes
                val transacoesFiltradas = transacoesFiltradas(it, transacoesSalvas)
                transacoes = transacoesFiltradas
                configuraAdapter()
            }, {
                transacoes = TransacaoDAO().transacoes
                configuraAdapter()
            })
        }
        return super.onOptionsItemSelected(item)
    }

    fun saoIguais(data: Calendar, dataDaTransacao: Calendar): Boolean {
        return data.get(Calendar.MONTH) == dataDaTransacao.get(Calendar.MONTH) && data.get(Calendar.YEAR) == dataDaTransacao.get(Calendar.YEAR)
    }

    private fun transacoesFiltradas(data: Calendar, transacoesSalvas: List<Transacao>): MutableList<Transacao> {
        val transacoesFiltradas = mutableListOf<Transacao>()
        for (transacao in transacoesSalvas) {
            val dataDaTransacao = transacao.data
            if (saoIguais(data, dataDaTransacao)) {
                transacoesFiltradas.add(transacao)
            }
        }
        return transacoesFiltradas
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = menuInfo.position
        if (item.itemId == 1) {
            removeTransacao(position)
        }
        return super.onContextItemSelected(item)
    }

    private fun atualizaTransacoes() {
        transacoes = TransacaoDAO().transacoes
        configuraAdapter()
    }

    private fun configuraAdapter() {
        adapter = ListaTransacaoAdapter(this, transacoes)
        lista_transacoes_listview.adapter = adapter
        atualizaResumo()
    }

    private fun atualizaResumo() {
        val resumo = Resumo(transacoes)
        resumoView.atualiza(resumo)
    }

    private fun adicionaTransacao(transacao: Transacao) {
        TransacaoDAO().adiciona(transacao)
        atualizaTransacoes()
    }

    private fun removeTransacao(position: Int) {
        TransacaoDAO().remove(position)
        atualizaTransacoes()
    }

    private fun alteraTransacao(transacao: Transacao, posicao: Int) {
        TransacaoDAO().altera(transacao, posicao)
        atualizaTransacoes()
    }
}
