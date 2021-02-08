package com.myProj.agenda.model

import java.io.Serializable

class Aluno: Serializable {

    var id: Long? = 0
    var nome: String? = null
    var telefone: String? = null
    var email: String? = null

    constructor(id: Long, nome:String, telefone: String, email: String){
        this.id = id
        this.nome = nome
        this.telefone = telefone
        this.email = email
    }

    constructor()

    override fun toString(): String {
        return "$nome -" +
                "$telefone"
    }

    fun isIdValido(): Boolean {
        return id!! > 0
    }

}
