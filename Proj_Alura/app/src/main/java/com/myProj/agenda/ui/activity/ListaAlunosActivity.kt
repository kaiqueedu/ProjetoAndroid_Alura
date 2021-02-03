package com.myProj.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.myProj.agenda.DAO.AlunoDAO
import com.myProj.agenda.R
import com.myProj.agenda.model.Aluno


class ListaAlunosActivity: AppCompatActivity() {

    private var TITULO_APPBAR = "Lista de Alunos"
    lateinit var botaoNovoAluno: FloatingActionButton
    lateinit var adapter: ArrayAdapter<Aluno>
    val dao = AlunoDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)

        title = TITULO_APPBAR

        configuraFabNovoAluno()
        configuraLista()

        dao.salva(Aluno(99, "Jack", "33362829", "const@rebou.com"))
        dao.salva(Aluno(98, "Ramiro", "123456789", "ramiro@sprint.com"))

    }

    override fun onResume() {
        super.onResume()
        atualizarAluno()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activyti_lista_alunos_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_remover){
            var menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            var alunoRemover: Aluno = adapter.getItem(menuInfo.position)!!
            remove(alunoRemover)
        }
        return super.onContextItemSelected(item)
    }

    private fun configuraLista() {
        val listaAlunos: ListView = findViewById(R.id.activity_lista_alunos_listview)
        configAdapter(listaAlunos)
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

    private fun configAdapter(listaAlunos: ListView){
        adapter = ArrayAdapter(
                this,
                R.layout.item_aluno )



        /*
        * getCount() - tamanho da lista
        *
        * getItem() - pegar um item na posicao
        *
        * getItemId() - id do elemento que estamos pegando
        *
        * getView() - onde cria a view do adapter{
        *   LayoutInflater.from(this).inflate(R.layout.item_aluno, viewGroup);
        *  return view;
        *
        * }
        *
        * */

    }



    fun atualizarAluno(){
        adapter.clear()
        adapter.addAll(dao.getListAlunos())
    }

    private fun remove(aluno: Aluno) {
        dao.remover(aluno)
        adapter.remove(aluno)
    }

}