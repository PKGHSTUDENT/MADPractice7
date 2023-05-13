package com.example.solveanexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var result: Int = 0
    private var countSolvedTasks: Int = 0
    private var countRightSolvedTasks: Int = 0
    private var countWrongSolvedTasks: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(icicle: Bundle) {
        icicle.putLong("param", 1)
        super.onSaveInstanceState(icicle)
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResumed called", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause called", Toast.LENGTH_LONG).show()
    }


    private fun setActivityBackgroundColor(color: Int): Unit {
        val view = this.window.decorView
        view.setBackgroundColor(color)
    }

    private fun setEnableButton(button: Button, set: Boolean): Unit {
        var buttonStart: Button = findViewById(R.id.buttonStart)
        var buttonCheck: Button = findViewById(R.id.buttonCheck)
        button.isEnabled = set
    }

    private fun setStatistic() {
        val solved: TextView = findViewById<TextView>(R.id.textViewCountSolverTasks)
        val solvedRight: TextView = findViewById<TextView>(R.id.textViewRightSolveTasks)
        val solvedWrong: TextView = findViewById<TextView>(R.id.textViewWrongSolveTasks)
        val solvedTasksPercent: TextView = findViewById<TextView>(R.id.textViewProcentSolvedTasks)
        solved.text = this.countSolvedTasks.toString()
        solvedRight.text = this.countRightSolvedTasks.toString()
        solvedWrong.text = this.countWrongSolvedTasks.toString()
        val percent = BigDecimal(this.countRightSolvedTasks * 100 / this.countSolvedTasks).setScale(2, RoundingMode.HALF_EVEN)
        solvedTasksPercent.text = "$percent%"
    }

    fun startButtonClick(view: View) {
        val firstOperand = findViewById<TextView>(R.id.textViewFirstOperand)
        val secondOperand = findViewById<TextView>(R.id.textViewSecondOperand)
        val operation = findViewById<TextView>(R.id.textViewOperatiron)

        setActivityBackgroundColor(0xFFFFFF)
        var buttonStart: Button = findViewById(R.id.buttonStart)
        var buttonCheck: Button = findViewById(R.id.buttonCheck)
        buttonStart.isEnabled = false
        buttonCheck.isEnabled = true

        var randomOperation = getRandomOperation()
        var numberOne: Int = getRandomNumber()
        var numberTwo: Int = getRandomNumber()

        when (randomOperation) {
            '+' -> {
                this.result = numberOne + numberTwo
            }
            '-' -> {
                while (numberOne - numberTwo < 0) {
                    numberOne = getRandomNumber()
                    numberTwo = getRandomNumber()
                }
                this.result = numberOne - numberTwo
            }
            '*' -> {
                this.result = numberOne * numberTwo
            }
            '/' -> {
                while (numberOne % numberTwo != 0) {
                    numberOne = getRandomNumber()
                    numberTwo = getRandomNumber()
                }
                this.result = numberOne / numberTwo
            }
        }

        firstOperand.text = numberOne.toString()
        secondOperand.text = numberTwo.toString()
        operation.text = randomOperation.toString()
    }

    fun checkButtonClick(view: View) {
        var result = findViewById<EditText>(R.id.editTextNumber)
        this.countSolvedTasks += 1
        if (result.text.toString() == this.result.toString()) {
            setActivityBackgroundColor(0x00FF00)
            this.countRightSolvedTasks += 1
        } else {
            setActivityBackgroundColor(0xFF0000)
            this.countWrongSolvedTasks += 1
        }
        setStatistic()
        var buttonStart: Button = findViewById(R.id.buttonStart)
        var buttonCheck: Button = findViewById(R.id.buttonCheck)
        buttonStart.isEnabled = true
        buttonCheck.isEnabled = false

    }

    private fun getRandomNumber(): Int {
        return Random.nextInt(10, 100)
    }

    private fun getRandomOperation(): Char {
        val list = listOf('+', '-', '/', '*')
        return list.random()
    }
}