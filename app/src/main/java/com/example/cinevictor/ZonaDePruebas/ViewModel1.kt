package com.example.cinevictor.ZonaDePruebas

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*

class DatosViewModel : ViewModel() {
    private  val _datosVisibles = mutableStateOf(false)

    val datosVisibles: State<Boolean> get() = _datosVisibles

    fun mostrarTexto() {
        _datosVisibles.value = !_datosVisibles.value

    }
}
