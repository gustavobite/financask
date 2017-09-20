package br.com.alura.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import com.github.clans.fab.FloatingActionButton
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private lateinit var transacoes: List<Transacao>
    private lateinit var view: View
    private lateinit var resumoView: ResumoView
    private lateinit var adapter: ListaTransacaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        view = window.decorView
        transacoes = TransacaoDAO().transacoes
        resumoView = ResumoView(this,
                view)
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
            AdicionaTransacaoDialog(this@ListaTransacoesActivity, view)
                    .mostraFormulario(tipo, {
                        atualiza(it)
                    })
            lista_transacoes_adiciona_menu.close(true)
        })
    }

    private fun configuraListaTransacoes() {
        configuraAdapter()
        lista_transacoes_listview.setOnCreateContextMenuListener { contextMenu, _, _ ->
            contextMenu.add(Menu.NONE, 1, Menu.NONE, "Remover")
        }

        lista_transacoes_listview.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, posicao, _ ->
                    val transacaoDevolvida = transacoes[posicao]
                    AlteraTransacaoDialog(this@ListaTransacoesActivity, view)
                            .mostraFormulario(transacaoDevolvida, {
                                altera(it, posicao)
                            })
                }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = menuInfo.position
        if (item.itemId == 1) {
            remove(position)
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
        resumoView.atualiza(Resumo(transacoes.toMutableList()))
    }

    private fun atualiza(transacao: Transacao) {
        TransacaoDAO().adiciona(transacao)
        atualizaTransacoes()
    }

    private fun remove(position: Int) {
        TransacaoDAO().remove(position)
        atualizaTransacoes()
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        TransacaoDAO().altera(transacao, posicao)
        atualizaTransacoes()
    }
}
