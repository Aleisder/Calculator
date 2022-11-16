package com.example.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    //represent the the string view of math expression
    private val _textExpr = MutableLiveData("0")
    val textExpr: LiveData<String> = _textExpr

    //stack of numbers
    private val numbersStack = mutableListOf<Int>()
    private val operationsStack = mutableListOf<String>()

    private val signList = listOf("*", "/", "+", "-", ".")

    //when user press any button, its content gets pushed to the end of expression
    fun changeTextExpression(text: String) {
        if (_textExpr.value == "0") {
            _textExpr.value = ""
        }
        if (signList.contains(text)) {
            numbersStack.add(_textExpr.value!!.toInt())
            operationsStack.add(text)
        }
        _textExpr.value = "${_textExpr.value}$text"
    }

    //clear all
    fun clearTextExpression() {
        _textExpr.value = "0"
    }

    //remove the last symbol from the expression
    fun clearLastSign() {
        _textExpr.value = _textExpr.value!!.dropLast(1)
        if (_textExpr.value == "") {
            _textExpr.value = "0"
        }
    }


    fun addOperation(operation: String) {
        val lastSymbol = (_textExpr.value!!).reversed()[0].toString()
        if (!signList.contains(lastSymbol)) {
            _textExpr.value = (_textExpr.value).plus(operation)
        }
    }

    fun addDot() {
        val lastSymbol = (_textExpr.value!!).reversed()[0].digitToIntOrNull()
        if ((0..9).toList().contains(lastSymbol)) {
            _textExpr.value = (_textExpr.value).plus(".")
        }
    }

    fun toSolve() {

    }

}