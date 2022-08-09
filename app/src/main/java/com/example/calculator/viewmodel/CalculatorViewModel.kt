package com.example.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    private val _textExpr = MutableLiveData("")
    val textExpr: LiveData<String> = _textExpr

    private val signList = listOf("*", "/", "+", "-", ".")

    fun changeTextExpression(text: String) {
        _textExpr.value = "${_textExpr.value}$text"
    }

    fun clearTextExpression() {
        _textExpr.value = ""
    }

    fun clearLastSign() {
        _textExpr.value = _textExpr.value!!.dropLast(1)
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

}