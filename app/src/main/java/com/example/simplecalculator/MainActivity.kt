package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    // ATTRIBUTES //
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

        buttonEquals.setOnClickListener { calculate() }
    } // onCreate

    private fun initializeNumberButtons(numButtons: Array<Button>) {
        for (button in numButtons) {
            button.setOnClickListener {
                if (input.toString() == "0") {
                    return@setOnClickListener
                }

                input.append(button.text)

                mainTextView.text = output.toString() + input.toString()
                modePressed = false
            } // setOnClickListener
        } // for
    } // initializeNumberButtons

    private fun initializeModeButtons(modeButtons: Array<Button>) {
        for (button in modeButtons) {
            button.setOnClickListener {
                if (!modePressed && output.isEmpty()) {
                    output.append("0" + button.text)
                }
                else if (!modePressed) {
                    output.append(input.toString() + button.text)
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

    private fun calculate() {
        if (modePressed) {
            return
        }

        if (output.isEmpty()) {
            return
        }

        if (input.isEmpty()) {
            return
        }

        output.append(input.toString())
        input.clear()

        var result = 0
        var currentNumber = StringBuilder()
        val numStack = Stack<Int>()

        for (c in output.toString()) {
            when (c) {
                '+' -> {
                    numStack.push(currentNumber.toString().toInt())
                    currentNumber.clear()
                }
                '-' -> {
                    numStack.push(currentNumber.toString().toInt())
                    currentNumber.clear()
                    currentNumber.append('-')
                }
                '*' -> {
                    val prevNum = numStack.pop()
                    val multResult = prevNum * currentNumber.toString().toInt()

                    numStack.push(multResult)
                    currentNumber.clear()
                }
                else -> currentNumber.append(c)
            }
        } // for

        while (numStack.isNotEmpty()) {
            result += numStack.pop()
        } // while

        output.clear()
        output.append(result)
        mainTextView.text = output.toString()
    } // calculate()

} // MainActivity
