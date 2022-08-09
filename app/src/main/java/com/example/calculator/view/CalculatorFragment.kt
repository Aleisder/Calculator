package com.example.calculator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.calculator.databinding.FragmentCalculatorBinding
import com.example.calculator.viewmodel.CalculatorViewModel


class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calculatorViewModel.textExpr.observe(viewLifecycleOwner) {
            binding.tvMathExpression.text = it
        }

        calculatorViewModel.textExpr.observe(viewLifecycleOwner) {
            when (it.length) {
                0 -> binding.tvMathExpression.textSize = 80.0F
                8 -> binding.tvMathExpression.textSize = 50.0F
            }
        }

        binding.apply {

            //Cleaning buttons
            btClear.setOnClickListener { clearExpression() }
            btClearLastSign.setOnClickListener { clearLastSign() }

            //Numeric buttons
            bt0.setOnClickListener { addNum(bt0.text.toString()) }
            bt00.setOnClickListener { addNum(bt00.text.toString()) }
            bt1.setOnClickListener { addNum(bt1.text.toString()) }
            bt2.setOnClickListener { addNum(bt2.text.toString()) }
            bt3.setOnClickListener { addNum(bt3.text.toString()) }
            bt4.setOnClickListener { addNum(bt4.text.toString()) }
            bt5.setOnClickListener { addNum(bt5.text.toString()) }
            bt6.setOnClickListener { addNum(bt6.text.toString()) }
            bt7.setOnClickListener { addNum(bt7.text.toString()) }
            bt8.setOnClickListener { addNum(bt8.text.toString()) }
            bt9.setOnClickListener { addNum(bt9.text.toString()) }

            //Fractional number button (.)
            btDot.setOnClickListener { addDot() }

            //Operation buttons
            btMultiply.setOnClickListener { addOperation(btMultiply.text.toString()) }
            btDifference.setOnClickListener { addOperation(btDifference.text.toString()) }
            btSum.setOnClickListener { addOperation(btSum.text.toString()) }
            btDivide.setOnClickListener { addOperation(btDivide.text.toString()) }

            //Solve button (=)
        }
    }

    //Add the new sign to the end of expression after the touch on the button
    private fun addNum(sign: String) {
        calculatorViewModel.changeTextExpression(sign)
    }

    private fun clearExpression() {
        calculatorViewModel.clearTextExpression()
    }

    private fun clearLastSign() {
        calculatorViewModel.clearLastSign()
    }

    private fun addOperation(operation: String) {
        calculatorViewModel.addOperation(operation)
    }

    private fun addDot() {
        calculatorViewModel.addDot()
    }


}