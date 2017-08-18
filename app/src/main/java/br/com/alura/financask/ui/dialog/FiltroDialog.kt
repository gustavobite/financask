package br.com.alura.financask.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import br.com.alura.financask.R
import java.util.*

/**
 * Created by alex on 16/08/17.
 */

class FiltroDialog(private val context: Context, viewRoot: ViewGroup) {

    private val viewCriada: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.form_filtro_transacao, viewRoot, false)
    }
    private val entradaMes: Spinner by lazy {
        viewCriada.findViewById<Spinner>(R.id.form_filtro_transacao_mes)
    }
    private val entradaAno: Spinner by lazy {
        viewCriada.findViewById<Spinner>(R.id.form_filtro_transacao_ano)
    }

    init {
        adicionaMeses()
        adicionaAnos()
    }

    fun mostraFormulario(delegate: (Calendar) -> Unit, limpaFiltro: () -> Unit) {
        val dialog = AlertDialog.Builder(context)
                .setTitle("Filtro")
                .setView(viewCriada)
                .setPositiveButton("Filtrar") { _, _ ->
                    val posicaoDoMes = entradaMes.selectedItemPosition
                    val ano = entradaAno.selectedItem as String
                    val dataDevolvida = Calendar.getInstance()
                    dataDevolvida.set(Calendar.YEAR, Integer.parseInt(ano))
                    dataDevolvida.set(Calendar.MONTH, posicaoDoMes)
                    delegate(dataDevolvida)
                }.setNegativeButton("Limpar filtro") { _, _ -> limpaFiltro() }.create()
        configuraCorDoBotaoPositivo(dialog)
        dialog.show()
    }

    private fun adicionaAnos() {
        val anoAtual = Calendar.getInstance().get(Calendar.YEAR)
        val anos = ArrayList<String>()
        val dezAnosAntes = anoAtual - 10
        val dezAnosDepois = anoAtual + 10
        for (ano in dezAnosAntes..dezAnosDepois) {
            anos.add(ano.toString())
        }
        val ano = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, anos)
        entradaAno.adapter = ano
        val metadeDoTamanho = anos.size / 2
        entradaAno.setSelection(metadeDoTamanho)
    }

    private fun adicionaMeses() {
        val meses = R.array.meses
        val adapterMeses = ArrayAdapter.createFromResource(context,
                meses, android.R.layout.simple_spinner_item)
        adapterMeses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        entradaMes.adapter = adapterMeses
    }

    private fun configuraCorDoBotaoPositivo(dialog: AlertDialog) {
        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
        }
    }

}
