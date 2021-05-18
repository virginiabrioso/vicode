package my.example1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {

    var campoDeNome : EditText? = null
    var campoDeData : EditText? = null
    var campoDePresente : EditText? = null
    lateinit var resultado: TextView
    lateinit var calcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        campoDeNome = findViewById(R.id.edtNome)
        campoDeData = findViewById(R.id.edtData)
        campoDePresente = findViewById(R.id.edtPresente)

        calcular = findViewById(R.id.btnCalcular)
        resultado = findViewById(R.id.txtResultado)

        calcular.setOnClickListener {
            //Tudo a ser executado, será somente quando o usuário tocar no botão

            val nome = campoDeNome?.text.toString()
            if(nome == ""){
                campoDeNome?.error = "O nome não foi inserido corretamente"
            }
            val aniversario = campoDeData?.text.toString()
            if(aniversario == ""){
                campoDeData?.error = "O aniversario não foi inserido corretamente"
            }
            val presente = campoDePresente?.text.toString()
            if(presente == ""){
                campoDePresente?.error = "O presente não foi inserido corretamente"
            }

            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            var aniversarioFormatado = LocalDate.parse(aniversario, formatter)

            val date = LocalDate.now()
            val diff = ChronoUnit.DAYS.between(date, aniversarioFormatado)

            //atualiza o campo text do Text View de Id=txtResultado
            resultado = findViewById(R.id.txtResultado)
            resultado.text = "Olá $nome,\nfaltam $diff dias para o seu aniversário!\nEspero que você ganhe um(a) $presente"
        }

    }

}