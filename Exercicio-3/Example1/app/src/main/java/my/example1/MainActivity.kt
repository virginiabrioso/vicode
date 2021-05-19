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

    var mutableListString: MutableList<String> = mutableListOf<String>()

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
            if(nome == ""){ campoDeNome?.error = "O nome não foi inserido corretamente" }

            val presente = campoDePresente?.text.toString()
            if(presente == ""){ campoDePresente?.error = "O presente não foi inserido corretamente" }

            val aniversario = campoDeData?.text.toString()
            if(aniversario == ""){ campoDeData?.error = "O aniversario não foi inserido corretamente" }
            else {
                var date = LocalDate.now()
                var hojeAno = date.getYear()
                var hojeMes = date.getMonthValue()

                var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                var nascimentoFormatado = LocalDate.parse(aniversario, formatter)
                var nascimentoDia = nascimentoFormatado.getDayOfMonth()
                var nascimentoMes = nascimentoFormatado.getMonthValue()

                if (hojeMes > nascimentoMes ){
                    hojeAno = hojeAno + 1
                    println(hojeAno)
                }

                var nascimentoProxFormatado = LocalDate.of(hojeAno,nascimentoMes, nascimentoDia)
                val diff = ChronoUnit.DAYS.between(date, nascimentoProxFormatado)

                val frase = "Faltam $diff dias para o aniversário de $nome!\n Ela(e) quer um(a) $presente"
                mutableListString.add(frase)

                //atualiza o campo text do Text View de Id=txtResultado


            }

            var frase = ""
            for(element in mutableListString){
                frase = frase +"\n"+ element
                resultado = findViewById(R.id.txtResultado)
                resultado.text = frase
            }

        }

    }

}