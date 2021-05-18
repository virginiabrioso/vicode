package my.example1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

//bib do texview
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textoGlobal : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pega TextView com a id texto
        textoGlobal = findViewById(R.id.texto)

        //constante
        val welcome = "Olá, seja bem-vinda"

        //variaveis
        var numeroQualquer = 2500
        var numeroQualquer2 = 4800
         //variavel pega saída da função criada
        var soma = somar(numeroQualquer, numeroQualquer2)
         //variavel pega saída da função default para converter para string
        var stringSoma = soma.toString()

        //atualiza o campo text do Text View de Id=texto
        textoGlobal.text = "$welcome! \nO resultado de $numeroQualquer + $numeroQualquer2 é $stringSoma"
    }

    // função estilo expression body
    fun somar(a: Int, b: Int): Int = a + b

}