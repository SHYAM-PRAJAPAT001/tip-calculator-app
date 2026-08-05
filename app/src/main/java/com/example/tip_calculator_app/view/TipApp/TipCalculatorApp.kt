package com.example.tip_calculator_app.view.TipApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tip_calculator_app.ui.theme.TipcalculatorappTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TipCalculatorApp() {

    var totalAmountText by remember { mutableStateOf("")}
    var tipPercentageText by remember {mutableStateOf("")}
    var noOfPeopleText by remember {mutableStateOf("")}
    var isCalculate by remember {mutableStateOf(false)}
    var Result by remember {mutableStateOf(TipResult(0.0, 0.0, 0.0, 0.0))}
    var isLoading by remember {mutableStateOf(false)}
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ){

        Text(
            text = "Calculate Tip",
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )

        TextFieldEdit(
            label = "Total Amount",
            value = totalAmountText,
            onValueChange = { totalAmountText = it },
            iconValue = Icons.Default.Money,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = androidx.compose.ui.text.input.ImeAction.Next
            )
        )

        TextFieldEdit(
            label = "Tip percentage",
            value = tipPercentageText,
            onValueChange = { tipPercentageText = it },
            iconValue = Icons.Default.Percent,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = androidx.compose.ui.text.input.ImeAction.Next
            )
        )

        TextFieldEdit(
            label = "Number of people",
            value = noOfPeopleText,
            onValueChange = { noOfPeopleText = it },
            iconValue = Icons.Default.Person,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            )
        )

        val totalAmount = totalAmountText.toDoubleOrNull() ?: 0.0
        val tipPercentage = tipPercentageText.toDoubleOrNull() ?: 0.0
        val noOfPeople = noOfPeopleText.toDoubleOrNull() ?: 0.0


        Button(
            onClick = {
                scope.launch{
                    isCalculate = true
                    isLoading = true
                    delay(2000)

                    Result = CalculateTip(
                        totalAmount = totalAmount,
                        tipPercentage = tipPercentage,
                        noOfPeople = noOfPeople
                    )

                    isLoading = false
                }
            }
        ){
            if(isLoading){

                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Calculating...")

            }else {
                Text(
                    text = "Calculate Tip",
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }


        if(isCalculate && !isLoading){
            ResultCard(Result)
        }

    }
}

fun CalculateTip(totalAmount: Double, tipPercentage: Double, noOfPeople: Double) : TipResult {

    val totalTipAmount = totalAmount * tipPercentage / 100
    val totAmount = totalAmount + totalTipAmount
    val perPersonAmount = totAmount / noOfPeople
    val perPersonTip = totalTipAmount / noOfPeople

    return TipResult(
        tipAmount = totalTipAmount,
        totalAmount = totAmount,
        perPersonAmount = perPersonAmount,
        perPersonTip = perPersonTip
    )
}

data class TipResult(
    val tipAmount: Double,
    val totalAmount: Double,
    val perPersonAmount: Double,
    val perPersonTip: Double
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipcalculatorappTheme {
        TipCalculatorApp()
    }
}