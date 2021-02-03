package com.myProj.agenda.DAO

import com.myProj.agenda.model.Aluno

class AlunoDAO {

    companion object {
        private var listAlunos: ArrayList<Aluno> = ArrayList()
        var contadorIds = 1
    }

    fun salva(aluno: Aluno) {
        val newAluno = Aluno(
                contadorIds,
                aluno.nome.toString(),
                aluno.telefone.toString(),
                aluno.email.toString()
        )
        listAlunos.add(newAluno)
        contadorIds++
    }

    fun editaAluno(aluno: Aluno){
        listAlunos.forEach{
            if(it.id == aluno.id){
                listAlunos.set(listAlunos.indexOf(it),aluno)
            }
        }
    }

    fun getListAlunos(): List<Aluno>{
        val novaLista: List<Aluno> = listAlunos
        return novaLista
    }

    fun remover(aluno: Aluno) {
        var alunoRemover = buscaAlunoPorId(aluno.id)
        if(alunoRemover != null){
            listAlunos.remove(alunoRemover)
        }

    }

    private fun buscaAlunoPorId(id: Int?): Aluno? {
        var alunoEncontrado: Aluno? = null

        if(id != null){
            listAlunos.forEach { a: Aluno ->
                if(a.id == id){
                    alunoEncontrado = a
                }
            }
        }
        return alunoEncontrado
    }

}
