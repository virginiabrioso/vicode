package com.example.exemplo5

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.ListView

open class Contato(
    val nome: String,
    val celular: String
){
    init {
        println("My name is $nome.")
        println("My phone is $celular")
    }

}
class Pessoal(nome: String, celular: String, val referencia: String) :
    Contato(nome, celular){
    override fun toString(): String {
        return " $nome, $celular, $referencia\n"
    }
    }

class Profissional(nome: String, celular: String, val email: String) :
    Contato(nome, celular){
    override fun toString(): String {
        return " $nome, $celular, $email\n"
    }
    }

class MainActivity : AppCompatActivity() {

    lateinit var campoDeNome: EditText
    lateinit var campoDeCelular: EditText
    lateinit var tipoCadastro: RadioGroup
    lateinit var radioButton: RadioButton
    lateinit var ref: TextView
    lateinit var referencia: EditText
    lateinit var salvar: Button
    lateinit var listagem: TextView

    private var contatos: MutableList<Contato> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        campoDeNome = findViewById(R.id.cameraType)
        campoDeCelular = findViewById(R.id.settings)
        tipoCadastro = findViewById(R.id.radioGroup)
        ref = findViewById(R.id.ref)
        referencia = findViewById(R.id.referencia)
        salvar = findViewById(R.id.upload)
        listagem = findViewById(R.id.txtListagem)


        salvar.setOnClickListener {
            var contatosexibidos: String = ""

            val nome = campoDeNome?.text.toString()
            if (nome == "") {
                campoDeNome?.error = "O nome não foi inserido corretamente"
            }

            val celular = campoDeCelular?.text.toString()
            if (celular == "") {
                campoDeCelular?.error = "O celular não foi inserido corretamente"
            }

            val ref = referencia?.text.toString()
            if (ref == "") {
                campoDeCelular?.error = "A ref não foi inserido corretamente"
            }

            val selectedOption: Int = tipoCadastro!!.checkedRadioButtonId
            radioButton = findViewById(selectedOption)
            val radioText = radioButton.text.toString()

            if (radioText == "Pessoal") {
                val newContact = Pessoal(nome, celular, ref)
                contatos.add(newContact)

            }
            if (radioText == "Profissional") {
                val newContact = Profissional(nome, celular, ref)
                contatos.add(newContact)
            }

            Toast.makeText(this, "Contato $nome do tipo $radioText foi salvo!", Toast.LENGTH_SHORT).show()

            for (x in contatos) {
                contatosexibidos += x.toString()
            }

            listagem.text = contatosexibidos

            contatos.sortBy { it.nome }

            campoDeNome.text = null
            campoDeCelular.text = null
            referencia.text = null

        }

    }

    fun onRadioButtonClicked(view: View) { //Função com os itens "checáveis".
        val selectedOption: Int = tipoCadastro!!.checkedRadioButtonId
        radioButton = findViewById(selectedOption)
        Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

        if (radioButton.text == "Pessoal") {
            ref.text = "Referência"
            referencia.setHint("Referência")
        }
        if (radioButton.text == "Profissional") {
            ref.text = "E-mail"
            referencia.setHint("E-mail")
        }

}
}