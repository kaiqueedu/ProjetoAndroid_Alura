package com.myProj.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.myProj.agenda.R
import com.myProj.agenda.model.Aluno

class ListaAlunosActivity: AppCompatActivity() {

    private var TITULO_APPBAR = "Lista de Alunos"
    lateinit var botaoNovoAluno: FloatingActionButton
    private val listaAlunosView = ListaAlunosView(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)

        title = TITULO_APPBAR

        configuraFabNovoAluno()
        configuraLista()

    }

    override fun onResume() {
        super.onResume()
        listaAlunosView.atualizarAluno()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activyti_lista_alunos_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_remover){
            //padrão de projeto usando builder, para add comportamento na instancia da classe
            listaAlunosView.confimarRemocao(item)
        }
        return super.onContextItemSelected(item)
    }

    private fun configuraLista() {
        val listaAlunos: ListView = findViewById(R.id.activity_lista_alunos_listview)
        listaAlunosView.configAdapter(listaAlunos)
        configListenerCliquePorItem(listaAlunos)
        //setando o menu na view
        registerForContextMenu(listaAlunos)
    }

    private fun configListenerCliquePorItem(listaAlunos: ListView){
        listaAlunos.setOnItemClickListener(OnItemClickListener { adapterView, view, posicao, id ->
            val alunoEscolhido = adapterView.getItemAtPosition(posicao) as Aluno
            abrirFormEditaAluno(alunoEscolhido)
        })
    }

    private fun abrirFormEditaAluno(aluno: Aluno){
        var intent = Intent(this, FormAlunoActivity::class.java)
        intent.putExtra("aluno", aluno)
        startActivity(intent)
    }

    private fun configuraFabNovoAluno(){
        botaoNovoAluno = findViewById(R.id.activity_lista_aluno_fab_novo_aluno)
        botaoNovoAluno.setOnClickListener {
            startActivity(Intent(this, FormAlunoActivity::class.java))
        }
    }

}