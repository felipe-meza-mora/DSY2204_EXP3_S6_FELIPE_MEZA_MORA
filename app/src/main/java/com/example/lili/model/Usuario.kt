package com.example.lili.model

data class Usuario(
    val nombre: String,
    val correo: String,
    val password: String,
    val edad: Int,
    val recibirNotificaciones: Boolean
)