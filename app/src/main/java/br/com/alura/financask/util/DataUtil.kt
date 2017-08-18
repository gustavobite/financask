package br.com.alura.financask.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * Created by alex on 18/08/17.
 */

object DataUtil {

    val FORMATO_BRASILEIRO = SimpleDateFormat("dd/MM/yyyy")

    fun formataParaBrasileiro(calendar: Calendar): String {
        return FORMATO_BRASILEIRO.format(calendar.time)
    }

    @Throws(ParseException::class)
    fun converte(dataEmTexto: String): Calendar {
        val calendar = Calendar.getInstance()
        val date = FORMATO_BRASILEIRO.parse(dataEmTexto)
        calendar.time = date
        return calendar
    }

}
