package com.myProj.agenda.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import com.myProj.agenda.DAO.AlunoDAO
import com.myProj.agenda.model.Aluno
import com.myProj.agenda.ui.activity.adapter.ListaAlunosAdapters

class ListaAlunosView(val context: Context) {

    lateinit var adapter: ListaAlunosAdapters
    val dao = AlunoDAO()

    fun confimarRemocao(item: MenuItem) {
        var dialog = AlertDialog
                .Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Deseja removover o aluno?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", DialogInterface.OnClickListener { dialog, which ->
                    var menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
                    var alunoRemover: Aluno = adapter.getItem(menuInfo.position)!!
                    remove(alunoRemover)
                } )
                .show()
    }

    fun atualizarAluno(){
        adapter.atualiza(dao.getListAlunos())
    }

    private fun remove(aluno: Aluno) {
        dao.remover(aluno)
        adapter.remove(aluno)
    }

     fun configAdapter(listaAlunos: ListView){
        adapter = ListaAlunosAdapters(context)
        listaAlunos.adapter = adapter
    }

}