package com.myProj.agenda

import android.app.Application
import com.myProj.agenda.DAO.AlunoDAO
import com.myProj.agenda.model.Aluno
import kotlin.concurrent.thread

class AgendaApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        criaAlunoTeste()
    }

    private fun criaAlunoTeste() {
        var dao: AlunoDAO = AlunoDAO()
        dao.salva(Aluno(99, "Jack", "33362829", "const@rebou.com"))
        dao.salva(Aluno(98, "Ramiro", "123456789", "ramiro@sprint.com"))
    }

}