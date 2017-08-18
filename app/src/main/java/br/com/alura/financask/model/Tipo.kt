package br.com.alura.financask.model

enum class Tipo {
    RECEITA {
        override fun toString(): String {
            return "Receita"
        }
    },
    DESPESA {
        override fun toString(): String {
            return "Despesa"
        }
    }
}
