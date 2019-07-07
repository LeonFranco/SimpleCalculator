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
                if (output.toString() == "0") {
                    output.clear()
                }

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
                if (output.isEmpty() && input.isEmpty()) {
                    output.append(0)
                }

                if (!modePressed) {
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

        if (output[0] == '-') {
            output.insert(0, '0')
        }

        var result = 0
        var currentNumber = StringBuilder()
        val numStack = Stack<Int>()
        var multFlag = false

        for (c in output.toString()) {
            when (c) {
                '+' -> {
                    numStack.push(currentNumber.toString().toInt())
                    currentNumber.clear()

                    if (multFlag) {
                        val num1 = numStack.pop()
                        val num2 = numStack.pop()

                        numStack.push(num1 *  num2)
                        multFlag = false
                    }
                }
                '-' -> {
                    numStack.push(currentNumber.toString().toInt())
                    currentNumber.clear()
                    currentNumber.append('-')

                    if (multFlag) {
                        val num1 = numStack.pop()
                        val num2 = numStack.pop()

                        numStack.push(num1 *  num2)
                        multFlag = false
                    }
                }
                '*' -> {
                    numStack.push(currentNumber.toString().toInt())
                    currentNumber.clear()

                    if (multFlag) {
                        val num1 = numStack.pop()
                        val num2 = numStack.pop()

                        numStack.push(num1 *  num2)
                    }

                    multFlag = true
                }
                else -> currentNumber.append(c)
            }
        } // for

        numStack.push(currentNumber.toString().toInt())

        if (multFlag) {
            val num1 = numStack.pop()
            val num2 = numStack.pop()

            numStack.push(num1 *  num2)
        }

        while (numStack.isNotEmpty()) {
            result += numStack.pop()
        } // while

        output.clear()
        mainTextView.text = result.toString()
    } // calculate()

} // MainActivity
