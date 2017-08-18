package br.com.alura.financask.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * Created by alex on 18/08/17.
 */

val FORMATO_BRASILEIRO = SimpleDateFormat("dd/MM/yyyy")

fun Calendar.formataParaBrasileiro(): String {
    return FORMATO_BRASILEIRO.format(this.time)
}

@Throws(ParseException::class)
fun Calendar.converte(dataEmTexto: String): Calendar {
    val date = FORMATO_BRASILEIRO.parse(dataEmTexto)
    this.time = date
    return this
}