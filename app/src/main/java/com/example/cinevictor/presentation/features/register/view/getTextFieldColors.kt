package com.example.cinevictor.presentation.features.register.view

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getTextFieldColors(
    containerColor: Color = Color.Black,
    textColor: Color = Color.White,
    indicatorColor: Color = Color.White,
    unfocusedIndicatorColor: Color = Color.Gray,
    cursorColor: Color = Color.White,
    errorColor: Color = Color.Red,
    focusedLabelColor: Color = Color.White,
    unfocusedLabelColor: Color = Color.Gray,
    errorLabelColor: Color = Color.Red
): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        errorContainerColor = containerColor,
        focusedTextColor = textColor,
        unfocusedTextColor = textColor,
        errorTextColor = errorColor,
        focusedIndicatorColor = indicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        errorIndicatorColor = errorColor,
        cursorColor = cursorColor,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = unfocusedLabelColor,
        errorLabelColor = errorLabelColor
    )
}
