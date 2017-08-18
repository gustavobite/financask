package br.com.alura.financask.delegate

import java.util.Calendar

/**
 * Created by alex on 16/08/17.
 */

interface FiltroDelegate {

    fun executa(data: Calendar)

    fun limpaFiltro()

}
