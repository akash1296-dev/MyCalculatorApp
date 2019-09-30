package com.example.mycalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.util.*
import com.example.mycalculatorapp.ExpressionEvaluate as ExpressionEvaluate1

class MainActivity : AppCompatActivity() {

    var flag: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }



        tvAdd.setOnClickListener {
            appendOnExpression("+", false)
        }

        tvSub.setOnClickListener {
            appendOnExpression("-", false)
        }

        tvMultiply.setOnClickListener {
            appendOnExpression("*", false)
        }

        tvDivide.setOnClickListener {
            appendOnExpression("/", false)
        }

        tvOpen.setOnClickListener {
            appendOnExpression("(", false)
        }

        tvClose.setOnClickListener {
            appendOnExpression(")", false)
        }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }


        ivDelete.setOnClickListener {
            val res = tvExpression.text.toString()
            if (res.isNotEmpty()) {
                tvExpression.text = res.substring(0, res.length - 1)
            } else {
                tvExpression.text = ""
            }
        }

        /*tvEquals.setOnClickListener {
            try {

                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            }catch (e:Exception){
                 d("Exception"," message : " + e.message )
            }
        }*/

        tvEquals.setOnClickListener {
            val exp = tvExpression.text
            val obj= ExpressionEvaluate1()
            val result = obj.eval(exp)
            tvResult.text = result.toString()
        }
    }

fun appendOnExpression(res: String, clear: Boolean) {
    if (tvResult.text.isNotEmpty()) {
        tvExpression.text = ""
    }

    if (clear) {
        d("Number Pressed", "InsideIfClear")
        tvResult.text = ""
        tvExpression.append(res)
        //println(tvExpression.text)
    } else {
        tvExpression.append(tvResult.text)
        tvExpression.append(res)
        tvResult.text = ""
    }
}
}

