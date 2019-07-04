package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // HELPER CLASS //
    private enum class CalcMode {
        ADD,
        SUBTRACT,
        MULTIPLY,
        NONE
    } // calcMode


    // ATTRIBUTES //
    private var mode = CalcMode.NONE
    private var modePressed = false
    private val output = StringBuilder()


    // METHODS //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainTextView.text = "HELLO"
    } // onCreate

} // MainActivity
