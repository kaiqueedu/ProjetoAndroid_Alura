package com.myProj.agenda.ui.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.myProj.agenda.R
import com.myProj.agenda.model.Aluno

class ListaAlunosAdapters(private var context: Context) : BaseAdapter() {

    var alunos: MutableList<Aluno> = mutableListOf()

    override fun getCount(): Int {
        return alunos.size
    }

    override fun getItem(position: Int): Aluno {
        return alunos.get(position)
    }

    override fun getItemId(position: Int): Long {
        return alunos[position].id!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewCriada: View = criarView(parent)

        var alunoitem = alunos.get(position) as Aluno

        vincula(viewCriada, alunoitem)

        return viewCriada
    }

    private fun vincula(view: View, aluno: Aluno) {
        var nomeView = view.findViewById<View>(R.id.aluno_nome) as TextView
        var telView = view.findViewById<View>(R.id.aluno_telefone) as TextView
        nomeView.text = aluno.nome
        telView.text = aluno.telefone
    }

    private fun criarView(parent: ViewGroup?): View {
        var viewCriada: View = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false)
        return viewCriada
    }

    fun atualiza(alunos: MutableList<Aluno> ){
        this.alunos.clear()
        this.alunos.addAll(alunos)
        notifyDataSetChanged()
    }

    fun remove(aluno: Aluno) {
        alunos.remove(aluno)
        notifyDataSetChanged() //notificando o adapter para atualizar
    }

}

