package com.example.mycalculatorapp

import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.lang.UnsupportedOperationException
import java.util.*


class ExpressionEvaluate {
    fun eval(exp : CharSequence): Int {

        val od = Stack<Int>()
        var op = Stack<Char>()
        println("exp size is ${exp.indices}")
        for (i in exp.indices) {

            //condition for checking number
            if (exp[i] in '0'..'9') {
                od.push(exp[i].toString().toInt())
                val a : Int = od.peek()
            }
            //condition for checking operator
            else if (exp[i] == '+' || exp[i] == '-' || exp[i] == '*' || exp[i] == '/') {
                //checking precedence of operator
                while (!(op.empty()) && hasPrecedence(exp[i], op.peek()))
                    od.push(applyOp(op.pop(), od.pop(), od.pop()))

                op.push(exp[i])
            } else if (exp[i] == '(') {
                op.push(exp[i])
            } else if (exp[i] == ')') {
                while (op.peek() != '(') {
                    od.push(applyOp(op.pop(),od.pop(),od.pop()))
                }
                val x = op.pop()
                println("last popped operator is $x")
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
        else if ( (op1 == '+') || (op1 == '-') || (op1 == '/') || (op1 == '*') && (op2 == '('))
            return false
        return true
    }
}