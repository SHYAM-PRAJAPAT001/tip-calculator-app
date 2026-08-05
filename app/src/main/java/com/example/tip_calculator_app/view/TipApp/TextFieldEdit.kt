package com.example.tip_calculator_app.view.TipApp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldEdit(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    iconValue: ImageVector,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
){

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {Text(label)} ,
        leadingIcon = {
            Icon(imageVector = iconValue , contentDescription = null)
        },
        singleLine = true,
        keyboardOptions = keyboardOptions
    )

    Spacer(modifier = Modifier.height(16.dp))

}