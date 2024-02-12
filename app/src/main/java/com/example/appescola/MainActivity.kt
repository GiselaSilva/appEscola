package com.example.appescola

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

        @SuppressLint("ServiceCast")
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                val edtN1: EditText = findViewById(R.id.edtN1)
                val edtN2: EditText = findViewById(R.id.edtN2)
                val btnCalc: Button = findViewById(R.id.btnCalc)
                val txtMedia: TextView = findViewById(R.id.txtMedia)
                val txtResult: TextView = findViewById(R.id.txtSituacao)
                val imgSituacao: ImageView = findViewById(R.id.imgSituacao)
                val layResult: LinearLayout = findViewById(R.id.layResult)
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


                layResult.visibility = INVISIBLE

                btnCalc.setOnClickListener {

                        val edtN1Str = edtN1.text.toString()
                        val edtN2Str = edtN2.text.toString()

                        //aqui faz só a validação de dados - se vazio
                        var ok: Boolean = true
                        if (edtN1Str.isEmpty()) { //se vazio se torna falso
                                ok = false
                                edtN1.error = getString(R.string.msgError) //setError do Java, aqui se torna .error
                        }
                        if (edtN2Str.isEmpty()) {
                                ok = false
                                edtN2.error = getString(R.string.msgError) //para acessarmos as strings das traduções
                        }
                        if (ok) { //aqui sim, faz o calculo da média
                                imm.run { hideSoftInputFromWindow(edtN2.windowToken, 0) }
                                layResult.visibility = View.VISIBLE
                                val n1: Float = edtN1Str.toFloat()
                                val n2: Float = edtN2Str.toFloat()
                                val media: Float = (n1 + n2) / 2
                                txtMedia.text = media.toString()

                                val situation = if (media < 5) { //reprovado
                                        txtResult.text = getString(R.string.strReprovado)
                                        txtResult.setTextColor(ContextCompat.getColor(this, R.color.red))
                                        Toast.makeText(applicationContext, getString(R.string.strMsgReprovado), Toast.LENGTH_SHORT).show()
                                        imgSituacao.setImageResource(R.drawable.reprovado)

                                } else if (media in 5f..7f) { //recuperacao
                                        txtResult.text = getString(R.string.strRecuperacao)
                                        txtResult.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                                        Toast.makeText(applicationContext, getString(R.string.strMsgRecuperacao), Toast.LENGTH_SHORT).show()
                                        imgSituacao.setImageResource(R.drawable.recuperacao)

                                } else { //aprovado
                                        txtResult.text = getString(R.string.strAprovado)
                                        txtResult.setTextColor(ContextCompat.getColor(this, R.color.green))
                                        Toast.makeText(applicationContext, getString(R.string.strMsgAprovado), Toast.LENGTH_SHORT).show()
                                        imgSituacao.setImageResource(R.drawable.aprovado)
                                }

                        }
                }


        }
}