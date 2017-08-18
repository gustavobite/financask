package br.com.alura.financask.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.EditText
import android.widget.Toast
import br.com.alura.financask.R
import br.com.alura.financask.model.Tipo
import br.com.alura.financask.model.Tipo.RECEITA
import br.com.alura.financask.model.Transacao
import br.com.alura.financask.util.DataUtil
import br.com.alura.financask.util.MoedaUtil
import java.text.ParseException
import java.util.*

/**
 * Created by alex on 16/08/17.
 */

open abstract class FormularioTransacaoDialog(protected var context: Context, viewRoot: ViewGroup) {

    private val viewCriada: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.form_transacao, viewRoot, false)
    }
    protected val valor: EditText by lazy {
        viewCriada.findViewById<EditText>(R.id.form_transacao_valor)
    }
    protected val categoria: Spinner by lazy {
        viewCriada.findViewById<Spinner>(R.id.form_transacao_categoria)
    }
    protected val data: EditText by lazy {
        viewCriada.findViewById<EditText>(R.id.form_transacao_data)
    }

    init {
        adicionaCalendario()
    }

    protected fun mostraDialog(tipo: Tipo,
                               delegate: (Transacao) -> Unit,
                               titulo: String,
                               tituloPositivo: String,
                               tituloNegativo: String) {
        configuraSpinner(tipo)
        AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewCriada)
                .setPositiveButton(tituloPositivo) { _, _ ->
                    val transacaoDevolvida = devolveTransacao(tipo)
                    delegate(transacaoDevolvida)
                }
                .setNegativeButton(tituloNegativo, null).show()
    }

    private fun configuraSpinner(tipo: Tipo) {
        var categorias = R.array.categorias_de_despesa

        if (tipo == RECEITA) {
            categorias = R.array.categorias_de_receita
        }

        val adapter = ArrayAdapter.createFromResource(context,
                categorias, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoria.adapter = adapter
    }

    private fun devolveTransacao(tipo: Tipo): Transacao {
        val categoria = this.categoria.selectedItem.toString()
        val valor = this.valor.text.toString()
        val data = this.data.text.toString()

        var calendar: Calendar = try {
            DataUtil.converte(data)
        } catch (e: ParseException) {
            Toast.makeText(context, "Falha ao inserir uma data", Toast.LENGTH_SHORT)
            e.printStackTrace()
            Calendar.getInstance()
        }

        val valorReal = MoedaUtil.validaMoeda(valor)
        return Transacao(valor = valorReal, tipo = tipo, data = calendar, categoria = categoria)
    }

    private fun adicionaCalendario() {
        data.setOnClickListener { chamaDatePicker(Calendar.getInstance()) }
    }

    private fun chamaDatePicker(calendar: Calendar) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(context, configuraDatePicker(), year, month, day).show()
    }

    private fun configuraDatePicker(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { datePicker, ano, mes, dia ->
            val calendar = Calendar.getInstance()
            calendar.set(ano, mes, dia)
            val formatoBrasileiro = DataUtil.formataParaBrasileiro(calendar)
            data.setText(formatoBrasileiro)
        }
    }
}
