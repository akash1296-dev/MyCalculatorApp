package com.example.mycalculatorapp

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.UnsupportedOperationException
import java.util.*


class ExpressionEvaluate {
    fun eval(exp : CharSequence): Int {

        val od = Stack<Int>()
        var op = Stack<Char>()
        println("exp size is ${exp.indices}")
        for (i in exp.indices) {

            //println("${exp[i]} ${exp[i].toString().toInt()}")
            if (exp[i] in '0'..'9') {
                //println("expis is $")
                od.push(exp[i].toString().toInt())
                val a : Int = od.peek()
                //println("a is $a")
            } else if (exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/') {
                while (!(op.empty()) && hasPrecedence(exp[i], op.peek()))
                    od.push(applyOp(op.pop(), od.pop(), od.pop()))

                op.push(exp[i])
            }
        } // endof For loop

        while (!op.empty())
            od.push(applyOp(op.pop(), od.pop(), od.pop()))

        println("Result is ${od.peek()}")
        return od.peek()
    }

    fun applyOp(op: Char, b: Int, a: Int) :Int {
        println("a is $a and b is $b")
        when (op) {
            '+' -> //println("a is $a and b is $b")
                    return a + b
            '-' -> return a - b
            '*' -> return a * b
            '/' -> {
                if (b == 0)
                    throw UnsupportedOperationException("Cannot divide by zero")
                return a / b
            }
        }
        return 0
    }

    fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false
        else if ((op1 == '+') && (op2 == '+'))
            return false
        else if ((op1 == '-') && (op2 == '-'))
            return false
        return true
    }
}