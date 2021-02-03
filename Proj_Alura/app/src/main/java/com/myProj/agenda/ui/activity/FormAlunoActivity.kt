package com.myProj.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.myProj.agenda.DAO.AlunoDAO
import com.myProj.agenda.R
import com.myProj.agenda.model.Aluno

class FormAlunoActivity : AppCompatActivity() {

    private val TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno"
    private val TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno"

    lateinit var editTextNome: EditText
    lateinit var editTextTelefone: EditText
    lateinit var editTextEmail: EditText

    private val dao = AlunoDAO()
    private lateinit var aluno: Aluno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_aluno)

        setLayout()
        carregaAluno()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_form_aluno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_menu_salvar){
            finalizaForm()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun carregaAluno(){
        var dados: Intent = intent
        if(dados.hasExtra("aluno")){
            title = TITULO_APPBAR_EDITA_ALUNO
            aluno = dados.getSerializableExtra("aluno") as Aluno
            preencheCampos()
        }else{
            title = TITULO_APPBAR_NOVO_ALUNO
            aluno = Aluno()
        }
    }

    private fun finalizaForm() {
        setAluno()
        if(aluno.isIdValido()){
            dao.editaAluno(aluno)
        }else{
            dao.salva(aluno)
        }
        finish()
    }

    private fun setLayout() {
        editTextNome = findViewById(R.id.activity_form_aluno_nome)
        editTextTelefone = findViewById(R.id.activity_form_aluno_telefone)
        editTextEmail = findViewById(R.id.activity_form_aluno_email)
    }

    private fun preencheCampos(){
        editTextNome.setText(aluno.nome)
        editTextTelefone.setText(aluno.telefone)
        editTextEmail.setText(aluno.email)
    }

    private fun setAluno() {
        aluno.nome = editTextNome.text.toString()
        aluno.email = editTextEmail.text.toString()
        aluno.telefone = editTextTelefone.text.toString()
    }

}