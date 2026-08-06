package com.example.tip_calculator_app

import com.example.tip_calculator_app.view.TipApp.CalculateTip
import com.example.tip_calculator_app.view.TipApp.TipResult
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TipAppTest {

    @Test
    fun testCalculateTip1() {

        val totalAmount = 100.00
        val tipAmount = 10.00
        val tipPerPerson = tipAmount / 2.0
        val totalPerPerson = (totalAmount + tipAmount) / 2.0

        val result = CalculateTip(totalAmount, 10.00, 2.0)

        assertEquals(
            TipResult(
                tipAmount = tipAmount,
                totalAmount = totalAmount + tipAmount,
                perPersonAmount = totalPerPerson,
                perPersonTip = tipPerPerson
            ),
            result
        )
    }

    @Test
    fun testCalculateTip2() {

        val totalAmount = 252.00
        val tipAmount = 63.00
        val tipPerPerson = tipAmount / 2.0
        val totalPerPerson = (totalAmount + tipAmount) / 2.0

        val result = CalculateTip(totalAmount, 25.00, 2.0)

        assertEquals(
            TipResult(
                tipAmount = tipAmount,
                totalAmount = totalAmount + tipAmount,
                perPersonAmount = totalPerPerson,
                perPersonTip = tipPerPerson
            ),
            result
        )
    }
}