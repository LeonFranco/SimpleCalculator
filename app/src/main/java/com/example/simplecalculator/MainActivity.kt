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

        mainTextView.text = "100"
    } // onCreate

    private fun pressNumButton(num: Int) {
        output.append(num)
        modePressed = false
    } // pressNumButton

    private fun pressedModeButton(modeSymbol: Char) {
        mode = when (modeSymbol) {
            '+' -> CalcMode.ADD
            '-' -> CalcMode.SUBTRACT
            '*' -> CalcMode.MULTIPLY
            else -> throw IllegalArgumentException()
        }

        if (modePressed) {
            output[output.lastIndex] = modeSymbol
        }
        else {
            output.append(modeSymbol)
            modePressed = true
        }
    } // pressedModeButton

    private fun pressedClear() {
        output.clear()
        modePressed = false
    } // pressedClear

    private fun pressedEnter() {

    } // pressedClear

} // MainActivity
