package com.myProj.agenda.ui.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.myProj.agenda.R
import com.myProj.agenda.model.Aluno

class ListaAlunosAdapters(private var context: Context) : BaseAdapter() {

    private var alunos: List<Aluno> = ArrayList()

    override fun getCount(): Int {

    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent)
    }
}