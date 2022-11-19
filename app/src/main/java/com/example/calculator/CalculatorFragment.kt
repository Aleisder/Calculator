package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.calculator.databinding.FragmentCalculatorBinding


class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(requireContext()))
        }
        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        _binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //textView on the top of the screen always show
        //the math expression from ViewModel
        viewModel.expression.observe(viewLifecycleOwner) {

            binding.tvMathExpression.text = it

            when (it.length) {
                1 -> binding.tvMathExpression.textSize = 80.0F
                8 -> binding.tvMathExpression.textSize = 50.0F
                13 -> binding.tvMathExpression.textSize = 30.0F
            }
        }

        binding.apply {

            setClickOnNums(bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9)

            // cleaning buttons
            btAllClear.setOnClickListener {
                clearExpression()
            }
            btBackspace.setOnClickListener {
                clearLastSign()
            }

            btDot.setOnClickListener {
                addDot()
            }

            //Operation buttons
            btMultiply.setOnClickListener { addOperation("*") }
            btDifference.setOnClickListener { addOperation("-") }
            btSum.setOnClickListener { addOperation("+") }
            btDivide.setOnClickListener { addOperation("/") }
            btnPercent.setOnClickListener { addOperation("%")}

            //Solve button (=)
            btCalculate.setOnClickListener {
                calculate()
            }
        }
    }

    private fun setClickOnNums(vararg buttons: Button) {
        for (button in buttons) {
            button.setOnClickListener {
                addNum(button.text.toString())
            }
        }
    }

    //Add the new sign to the end of expression after the touch on the button
    private fun addNum(sign: String) {
        viewModel.changeTextExpression(sign)
    }

    private fun clearExpression() {
        viewModel.clearAll()
    }

    private fun clearLastSign() {
        viewModel.backspace()
    }

    private fun addOperation(operation: String) {
        viewModel.addOperation(operation)
    }

    private fun addDot() {
        viewModel.addDot()
    }

    private fun calculate() {
        if (binding.tvMathExpression.text.last().isDigit()) {
            viewModel.calculate()
        }
    }

}