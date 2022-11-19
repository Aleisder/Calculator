package com.example.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chaquo.python.Python

class CalculatorViewModel : ViewModel() {

    private val python = Python.getInstance()
    private val pyModule = python.getModule("calculate")

    //represent the the string view of math expression
    private val expr = MutableLiveData("0")
    val expression: LiveData<String> = expr

    //when user press any button, its content gets pushed to the end of expression
    fun changeTextExpression(text: String) {
        expr.apply {
            if (value == "0" || value == "Error")  {
                value = ""
            }
            value = "${value}$text"
        }
    }

    fun clearAll() {
        expr.value = "0"
    }

    fun backspace() {
        expr.apply {
            value = value!!.dropLast(1)
            if (listOf("0", "Error", "").contains(value)) {
                value = "0"
            }
        }
    }

    fun addOperation(operation: String) {
        expr.apply {
            if (value!!.isNotEmpty() && value!!.last().isDigit()) {
                value = value.plus(operation)
            }
        }
    }

    // a dot will be added only if last sign is digit
    fun addDot() {
        expr.apply {
            if (value!!.last().isDigit()) {
                value = value.plus(".")
            }
        }
    }

    fun calculate() {
        val answer = pyModule.callAttr("main", expression.value.toString())
        expr.value = answer.toString()
    }

}