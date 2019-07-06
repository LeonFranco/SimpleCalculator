package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
    private val input = StringBuilder()
    private val output = StringBuilder()


    // METHODS //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainTextView.text = "0"

        val numButtons = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        initializeNumberButtons(numButtons)

        val modeButtons = arrayOf(buttonPlus, buttonMinus, buttonMultiply)
        initializeModeButtons(modeButtons)

        buttonClear.setOnClickListener { clear() }

    } // onCreate

    private fun initializeNumberButtons(numButtons: Array<Button>) {
        for (button in numButtons) {
            button.setOnClickListener {
                input.append(button.text)

                if (input.toString() == "0") {
                    input.clear()
                    return@setOnClickListener
                }

                mainTextView.text = output.toString() + input.toString()
                modePressed = false
            } // setOnClickListener
        } // for
    } // initializeNumberButtons

    private fun initializeModeButtons(modeButtons: Array<Button>) {
        for (button in modeButtons) {
            button.setOnClickListener {
                if (!modePressed && input.isNotEmpty()) {
                    output.append(input.toString() + button.text)
                }
                else if (!modePressed && input.isEmpty()) {
                    output.append("0" + button.text)
                }
                else {
                    output[output.lastIndex] = button.text[0]
                }

                input.clear()
                modePressed = true
                mainTextView.text = output.toString()
            } // setOnClickListener
        } // for
    } // initializeModeButtons

    private fun clear() {
        input.clear()
        output.clear()
        mainTextView.text = "0"
        modePressed = false
    } // clear

} // MainActivity
