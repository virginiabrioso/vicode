package my.example1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import kotlin.random.Random

class Elevador {
    val vagas = 5
    val andares = 10
    var andarAtual = Random.nextInt(0, andares)
    var lotacao = Random.nextInt(0, vagas)

    fun entrarElevador(andar: Int): Boolean {
        if (andar > andares || andar < 0 || lotacao == vagas){
            return false
        }else{
            lotacao = lotacao + 1
            andarAtual = andar
        }
        return true
    }

    fun sairElevador() {
        lotacao = lotacao - 1
        if(lotacao<0){
            lotacao = 0
        }
    }

}

class MainActivity : AppCompatActivity() {

    var campoDeN: EditText? = null
    lateinit var resultado: TextView
    lateinit var andar: TextView
    lateinit var entrar: Button
    lateinit var sair: Button

    var mutableListString: MutableList<String> = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val elevator = Elevador()
        println(elevator.andarAtual)
        println(elevator.lotacao)

        campoDeN = findViewById(R.id.edtN)
        entrar = findViewById(R.id.btnEntrar)
        sair = findViewById(R.id.btnSair)

        resultado = findViewById(R.id.txtResultado)
        resultado.text = "${elevator.lotacao}/${elevator.vagas}"

        andar = findViewById(R.id.num_andar)
        andar.text = "${elevator.andarAtual}º andar"

        entrar.setOnClickListener {
            val nstrng = campoDeN?.text.toString()

            if (nstrng == "") {
                campoDeN?.error = "Nada foi inserido."
            }

            val floor = nstrng.toIntOrNull()

            when (floor) {
                null -> mutableListString.add("Andar invalido")
                else -> {

                    elevator.entrarElevador(floor)
                    resultado.text = "${elevator.lotacao}/${elevator.vagas}"
                    andar.text = "${elevator.andarAtual}º andar"
                }
            }

        }

        sair.setOnClickListener {
            elevator.sairElevador()
            resultado.text = "${elevator.lotacao}/${elevator.vagas}"
        }

        }}



